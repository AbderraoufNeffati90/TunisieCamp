package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;

import java.util.List;

public interface CampSiteService {


    CampSite addCampsiteToCampingCenter(CampSite campsite, Integer idCentre);



    List<CampSite> getCampsitesByCampingCenter(String name);


    boolean isAvailable(CampSite campsite);

    void updateCampsite(String name, CampSite updatedCampsite);

    void deleteCampsiteByName(String name);

    List<CampSite> searchCampsites(String keyword);
}
