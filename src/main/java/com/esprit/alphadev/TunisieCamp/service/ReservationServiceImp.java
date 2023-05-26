package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReservationServiceImp implements ReservationService {
    ReservationRepository reservationRepository;
    CampSiteRepository campSiteRepository;



    @Override
    public void makeReservation(Reservation reservation, String name) {
        Reservation reservation1 = new Reservation();

        reservation1.setUser(reservation.getUser());
        reservation1.setCampingCenter(reservation.getCampingCenter());

        Optional<CampSite> campsiteOptional = Optional.ofNullable(campSiteRepository.findByName(name));
        if (campsiteOptional.isPresent()) {
            CampSite campsite = campsiteOptional.get();
            reservation1.setCampsite(campsite);
            reservation1.setStartDate(reservation.getStartDate());
            reservation1.setEndDate(reservation.getEndDate());

            reservationRepository.save(reservation1);
        } else {
            throw new IllegalArgumentException("Campsite not found with name: " + name);
        }
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





