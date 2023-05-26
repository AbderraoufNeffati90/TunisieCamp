package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.repository.ActivityRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampingCenterRepository;
import com.esprit.alphadev.TunisieCamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CampingCenterServiceImp implements CampingCenterService {
    UserRepository userRepository;
    CampingCenterRepository campingCenterRepository;
    CampSiteRepository campsiteRepository;
    ActivityRepository activityRepository;


    @Override
    public List<CampingCenter> getAllCampingCenters() {
        return campingCenterRepository.findAll();

    }
    @Override

    public CampingCenter getCampingCenterById(Long id) {

        return campingCenterRepository.findById(id).orElse(null);
    }
    @Override
    public void AddCampingCenter(CampingCenter campingCenter) {
        CampingCenter newCampingCenter = new CampingCenter();
        newCampingCenter.setName(campingCenter.getName());
        newCampingCenter.setAddress(campingCenter.getAddress());
        newCampingCenter.setPhoneNumber(campingCenter.getPhoneNumber());
        newCampingCenter.setEmail(campingCenter.getEmail());

        campingCenterRepository.save(newCampingCenter);
    }

@Override
    public void updateCampingCenter(String name, CampingCenter campingCenter) {
        Optional<CampingCenter> campingCenterOptional = campingCenterRepository.findByName(name);

        if (campingCenterOptional.isPresent()) {
            CampingCenter campingCenter1 = campingCenterOptional.get();
            campingCenter1.setName(campingCenter.getName());
            campingCenter1.setAddress(campingCenter.getAddress());
            campingCenter1.setPhoneNumber(campingCenter.getPhoneNumber());
            campingCenter1.setEmail(campingCenter.getEmail());

            campingCenterRepository.save(campingCenter);
        }
    }
@Override
    public void deleteCampingCenter(String name) {
        Optional<CampingCenter> campingCenterOptional = campingCenterRepository.findByName(name);

        campingCenterOptional.ifPresent(campingCenter -> {
            campingCenterRepository.delete(campingCenter);
        });
    }
@Override
    public List<CampingCenter> searchCampingCenters(String keyword) {
        // Perform a search query based on the keyword
        return campingCenterRepository.searchByNameContainingIgnoreCase(keyword);
    }


}
