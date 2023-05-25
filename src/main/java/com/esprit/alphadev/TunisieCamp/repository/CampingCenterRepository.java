package com.esprit.alphadev.TunisieCamp.repository;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CampingCenterRepository extends JpaRepository<CampingCenter,Long> {
    Optional<CampingCenter> findByName(String name);
    @Query("SELECT c FROM CampingCenter c WHERE LOWER(c.name) LIKE %:keyword%")
    List<CampingCenter> searchByNameContainingIgnoreCase(@Param("keyword") String keyword);
}
