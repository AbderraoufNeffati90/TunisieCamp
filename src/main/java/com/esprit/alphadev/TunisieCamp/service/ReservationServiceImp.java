package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReservationServiceImp implements ReservationService {
    ReservationRepository reservationRepository;
    CampSiteRepository campSiteRepository;



@Override
    public void makeReservation(Reservation reservation, String campsiteName) {

        Reservation reservation1 = new Reservation();

        reservation1.setUser(reservation1.getUser());
        reservation1.setCampingCenter(reservation1.getCampingCenter());

        CampSite campsite = campSiteRepository.findByName(campsiteName)
            .orElseThrow(() -> new IllegalArgumentException("Campsite not found"));
        reservation1.setCampsite(campsite);
        reservation1.setStartDate(reservation1.getStartDate());
        reservation1.setEndDate(reservation1.getEndDate());

        reservationRepository.save(reservation1);
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
    @Override
    public void confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID"));

        // Check if the campsite has sufficient capacity for the reservation
        CampSite campsite = reservation.getCampsite();
        if (campsite.getCapacity() >= reservation.getNumberOfPeople()) {
            reservation.setConfirmed(true);
            reservationRepository.save(reservation);
        } else {
            // Handle the case when the campsite doesn't have sufficient capacity
            throw new IllegalArgumentException("Campsite does not have sufficient capacity");
        }
    }

}





