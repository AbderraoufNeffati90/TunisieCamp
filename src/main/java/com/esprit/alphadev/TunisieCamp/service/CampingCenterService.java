package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;

import java.util.List;

public interface CampingCenterService {
    List<CampingCenter> getAllCampingCenters();

    CampingCenter getCampingCenterById(Integer id);






    void AddCampingCenter(CampingCenter campingCenter, Long userId);

    void updateCampingCenter(String name, CampingCenter campingCenter);

    void deleteCampingCenter(String name);

    List<CampingCenter> searchCampingCenters(String keyword);


}
