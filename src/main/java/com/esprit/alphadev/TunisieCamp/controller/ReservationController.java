package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    ReservationService reservationService;



    @PostMapping("/make")
    public ResponseEntity<String> makeReservation(@RequestBody Reservation reservation, @RequestParam String campsiteName) {
        try {
            reservationService.makeReservation(reservation, campsiteName);
            return ResponseEntity.ok("Reservation made successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public List<Reservation> getAllReservationsWithUsers() {
        return reservationService.getAllReservationsWithUsers();
    }



}
