package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CampingCenterServiceImp implements CampingCenterService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CampingCenterRepository campingCenterRepository;

    @Autowired
    CampSiteRepository campsiteRepository;

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    FeedbackRepository feedbackRepository;


    @Override
    public List<CampingCenter> getAllCampingCenters() {
        return campingCenterRepository.findAll();

    }
    @Override

    public CampingCenter getCampingCenterById(Long id) {

        return campingCenterRepository.findById(id).orElse(null);
    }
    @Override
    public void AddCampingCenter(CampingCenter campingCenter, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            campingCenter.setUser(user);
            campingCenterRepository.save(campingCenter);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
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
