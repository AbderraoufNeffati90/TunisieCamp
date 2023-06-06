package com.esprit.alphadev.TunisieCamp.repository;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampSiteRepository  extends JpaRepository<CampSite,Long> {
    @Query("SELECT c FROM CampSite c WHERE c.name LIKE %:name%")
    CampSite  findByName(@Param("name") String name );

    void deleteByName(String name);

    List<CampSite> findByCampingCenterNameLike(String name);

}
