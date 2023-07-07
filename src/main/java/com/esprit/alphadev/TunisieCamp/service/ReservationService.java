package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface ReservationService {


    void makeReservation(Reservation reservation, String name , Long   idUser);

    List<Reservation> getAllReservationsWithUsers();

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000) // Run once per day
    void updateCampsiteCapacity();

    boolean isCampsiteAvailable(CampSite campsite);


    void updateReservation(Integer reservationId, Reservation updatedReservation);


    void deleteReservation(Integer id);
}
