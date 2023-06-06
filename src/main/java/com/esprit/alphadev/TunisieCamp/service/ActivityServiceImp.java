package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Activity;
import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.repository.ActivityRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImp  implements ActivityService{
    @Autowired
    CampSiteRepository campSiteRepository;
    @Autowired
    ActivityRepository activityRepository;




    @Override

    public void addActivityToCampsite(Long campsiteId, @NotNull Activity activity) {
        // Retrieve the campsite by its ID
        CampSite campsite = campSiteRepository.findById(campsiteId)
                .orElseThrow(() -> new IllegalArgumentException("Campsite not found"));
        activity.setCampsite(campsite);
        activityRepository.save(activity);

    }

    @Override
    public List<Activity> getActivitiesByCampsiteName(String name) {
        // Retrieve the campsite by its name
        Optional<CampSite> optionalCampsite = Optional.ofNullable(campSiteRepository.findByName(name));

        if (optionalCampsite.isPresent()) {
            CampSite campsite = optionalCampsite.get();
            // Return the list of activities associated with the campsite
            return campsite.getActivities();
        } else {
            throw new IllegalArgumentException("Campsite not found");
        }
    }


}
