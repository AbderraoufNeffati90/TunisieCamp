package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Activity;
import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.repository.ActivityRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ActivityServiceImp  implements ActivityService{
    CampSiteRepository campSiteRepository;
    ActivityRepository activityRepository;

    @Override

    public void addActivityToCampsite(Long campsiteId, @NotNull Activity activity) {
        // Retrieve the campsite by its ID
        CampSite campsite = campSiteRepository.findById(campsiteId)
                .orElseThrow(() -> new IllegalArgumentException("Campsite not found"));
        activity.setCampsite(campsite);
        activityRepository.save(activity);
        campsite.getActivities().add(activity);
        campSiteRepository.save(campsite);
    }

    @Override
    public List<Activity> getActivitiesByCampsiteId(Long campsiteId) {
        // Retrieve the campsite by its ID
        CampSite campsite = campSiteRepository.findById(campsiteId)
                .orElseThrow(() -> new IllegalArgumentException("Campsite not found"));

        // Return the list of activities associated with the campsite
        return campsite.getActivities();
    }

}
