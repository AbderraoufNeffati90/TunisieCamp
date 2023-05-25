package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;

import java.util.List;

public interface CampSiteService {
    void addCampsiteToCampingCenter(Long campingCenterId, CampSite campsite);

    List<CampSite> getCampsitesByCampingCenter(Long campingCenterId);
}
