package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import com.esprit.alphadev.TunisieCamp.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    ReservationService reservationService;



    @PostMapping("/make/{idUser}")
    public ResponseEntity<String> makeReservation(@RequestBody Reservation reservation,
                                                  @RequestParam String campsiteName,
                                                  @PathVariable("idUser") Long idUser) {
        try {
            reservationService.makeReservation(reservation, campsiteName , idUser);
            return ResponseEntity.ok("Reservation created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public List<Reservation> getAllReservationsWithUsers() {

        return reservationService.getAllReservationsWithUsers();
    }
    @PutMapping("/{reservationId}")
    public ResponseEntity<String> updateReservation(@PathVariable Integer reservationId, @RequestBody Reservation updatedReservation) {
        try {
            reservationService.updateReservation(reservationId, updatedReservation);
            return ResponseEntity.ok("Reservation updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }



}
