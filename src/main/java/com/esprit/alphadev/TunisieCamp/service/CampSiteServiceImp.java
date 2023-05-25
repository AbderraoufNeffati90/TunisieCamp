package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampingCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CampSiteServiceImp implements CampSiteService{

     CampingCenterRepository campingCenterRepository;
     CampSiteRepository campsiteRepository;

     @Override

    public void addCampsiteToCampingCenter(Long campingCenterId, CampSite campsite) {
        // Retrieve the camping center by its ID
        CampingCenter campingCenter = campingCenterRepository.findById(campingCenterId)
                .orElseThrow(() -> new IllegalArgumentException("Camping center not found"));


        campsite.setCampingCenter(campingCenter);
        campsiteRepository.save(campsite);
        campingCenter.getCampsites().add(campsite);
        campingCenterRepository.save(campingCenter);
    }
    @Override
    public List<CampSite> getCampsitesByCampingCenter(Long campingCenterId) {
        // Retrieve the camping center by its ID
        CampingCenter campingCenter = campingCenterRepository.findById(campingCenterId)
                .orElseThrow(() -> new IllegalArgumentException("Camping center not found"));

        // Get the campsites associated with the camping center
        return campingCenter.getCampsites();
    }
}
