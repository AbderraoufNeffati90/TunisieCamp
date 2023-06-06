package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;

import java.util.List;

public interface CampSiteService {


    CampSite addCampsiteToCampingCenter(CampSite campsite, Long idCentre);



    List<CampSite> getCampsitesByCampingCenter(String name);


    boolean isAvailable(CampSite campsite);

    void updateCampsite(String name, CampSite updatedCampsite);

    void deleteCampsiteByName(String name);
}
