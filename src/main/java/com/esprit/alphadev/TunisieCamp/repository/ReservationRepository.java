package com.esprit.alphadev.TunisieCamp.repository;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
     Integer countByCampsite(CampSite campsite);
}
