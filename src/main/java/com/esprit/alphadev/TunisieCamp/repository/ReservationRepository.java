package com.esprit.alphadev.TunisieCamp.repository;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

     @Query("SELECT SUM(r.numberOfPeople) FROM Reservation r WHERE r.campsite = :campsite")
     Integer sumNumberOfPeopleByCampsite(@Param("campsite") CampSite campsite);

    @Query("SELECT r FROM Reservation r WHERE r.endDate < :currentDate")
    List<Reservation> findExpiredReservations(@Param("currentDate") Date currentDate);
}
