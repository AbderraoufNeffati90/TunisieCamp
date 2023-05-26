package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampingCenterRepository;
import com.esprit.alphadev.TunisieCamp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampSiteServiceImp implements CampSiteService{
    @Autowired

     CampingCenterRepository campingCenterRepository;
    @Autowired
     CampSiteRepository campsiteRepository;
    @Autowired
    ReservationRepository reservationRepository;

     @Override

     public CampSite addCampsiteToCampingCenter(CampSite campsite, Long idCentre) {
         Optional<CampingCenter> optionalCampingCenter = campingCenterRepository.findById(idCentre);
         if (optionalCampingCenter.isPresent()) {
             CampingCenter campingCenter = optionalCampingCenter.get();
             campsite.setCampingCenter(campingCenter);
             campingCenter.getCampsites().add(campsite);
             return campsiteRepository.save(campsite);
         } else {
             throw new IllegalArgumentException("Camping Center with ID " + idCentre + " not found.");
         }
     }

    @Override
    public List<CampSite> getCampsitesByCampingCenter(String name) {
        // Retrieve the camping center by its name
        CampingCenter campingCenter = campingCenterRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Camping center not found"));

        // Get the campsites associated with the camping center
        List<CampSite> campsites = campingCenter.getCampsites();

        // Iterate through the campsites and set their availability status
        for (CampSite campsite : campsites) {
            boolean isAvailable = isCampsiteAvailable(campsite);
            campsite.setIsAvailable(isAvailable);
        }

        return campsites;
    }

    @Override
    public boolean isCampsiteAvailable(CampSite campsite) {
        int currentOccupancy = reservationRepository.countByCampsite(campsite);
        return currentOccupancy < campsite.getCapacity();
    }


    @Override
    public void updateCampsite(String name, CampSite updatedCampsite) {
        CampSite existingCampsite = campsiteRepository.findByName(name);
        if (existingCampsite != null) {
            existingCampsite.setName(updatedCampsite.getName());
            existingCampsite.setDescription(updatedCampsite.getDescription());
            existingCampsite.setCapacity(updatedCampsite.getCapacity());
            existingCampsite.setPricePerNight(updatedCampsite.getPricePerNight());
            campsiteRepository.save(existingCampsite);
        }
    }

    @Override
    public void deleteCampsiteByName(String name) {
        campsiteRepository.deleteByName(name);
    }
}
