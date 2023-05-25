package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;

import java.util.List;

public interface ReservationService {

    void makeReservation(Reservation reservation, String name);

    List<Reservation> getAllReservationsWithUsers();

    void confirmReservation(Long reservationId);
}
