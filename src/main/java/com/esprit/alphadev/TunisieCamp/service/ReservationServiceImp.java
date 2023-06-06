package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReservationServiceImp implements ReservationService {
   @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CampSiteRepository campSiteRepository;
    @Autowired
    ReservationServiceImp reservationServiceImp;

    @Override
    public void makeReservation(Reservation reservation, String name) {
        // Retrieve the campsite by its name
        CampSite campsite = campSiteRepository.findByName(name);
        // Check if the campsite exists
        if (campsite == null) {
            throw new IllegalArgumentException("Campsite not found with name: " + name);
        }
        // Check if the campsite is available for the given number of people
        if (!isCampsiteAvailable(campsite)) {
            throw new IllegalArgumentException("Campsite is not available");
        }
        // Create the reservation
        Reservation newReservation = new Reservation();
        newReservation.setUser(reservation.getUser());
        newReservation.setCampingCenter(reservation.getCampingCenter());
        newReservation.setCampsite(campsite);
        newReservation.setStartDate(reservation.getStartDate());
        newReservation.setEndDate(reservation.getEndDate());
        newReservation.setNumberOfPeople(reservation.getNumberOfPeople());

        reservationRepository.save(newReservation);

        // Update the campsite capacity
        int currentOccupancy = reservationRepository.sumNumberOfPeopleByCampsite(campsite);
        int newCapacity = campsite.getCapacity() - currentOccupancy;
        campsite.setCapacity(newCapacity);
        campSiteRepository.save(campsite);

        // Update the campsite capacity for expired reservations
        reservationServiceImp.updateCampsiteCapacity();
    }

    @Override
    public List<Reservation> getAllReservationsWithUsers() {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            User user = reservation.getUser();
            user.setReservations(null);
        }
        return reservations;
    }
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000) // Run once per day
    @Override
    public void updateCampsiteCapacity() {
        // Get the list of expired reservations
        List<Reservation> expiredReservations = reservationRepository.findExpiredReservations(new Date());

        for (Reservation reservation : expiredReservations) {
            CampSite campsite = reservation.getCampsite();

            // Update the campsite capacity
            int currentOccupancy = reservationRepository.sumNumberOfPeopleByCampsite(campsite);
            int newCapacity = campsite.getCapacity() + reservation.getNumberOfPeople();
            campsite.setCapacity(newCapacity);
            campSiteRepository.save(campsite);

            // Delete the expired reservation
            reservationRepository.delete(reservation);
        }
    }


    @Override
    public boolean isCampsiteAvailable(CampSite campsite) {
        // Get the current occupancy of the campsite
        int currentOccupancy = reservationRepository.sumNumberOfPeopleByCampsite(campsite);

        // Calculate the remaining capacity of the campsite
        int remainingCapacity = campsite.getCapacity() - currentOccupancy;

        // Check if the remaining capacity is greater than or equal to the number of people in the reservation
        return remainingCapacity >= 1;
    }

}





