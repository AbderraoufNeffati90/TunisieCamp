package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import org.springframework.stereotype.Service;



public interface EmailService {


    void sendConfirmationEmail(Reservation reservation);

    void sendCancellationEmail(Reservation reservation);
}
