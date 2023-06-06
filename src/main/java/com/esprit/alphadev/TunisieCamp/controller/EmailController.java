package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")

public class EmailController {
    EmailService emailService;

    @PostMapping("/confirm")
    public ResponseEntity<String> sendConfirmationEmail(@RequestBody Reservation reservation) {
        try {
            emailService.sendConfirmationEmail(reservation);
            return ResponseEntity.ok("Confirmation email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send confirmation email");
        }
    }
    @PostMapping("/cancel")
    public ResponseEntity<String> sendCancellationEmail(@RequestBody Reservation reservation) {
        try {
            emailService.sendCancellationEmail(reservation);
            return ResponseEntity.ok("Cancellation email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send cancellation email");
        }
    }
}
