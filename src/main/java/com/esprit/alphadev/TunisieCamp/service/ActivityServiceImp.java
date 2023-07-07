package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Activity;
import com.esprit.alphadev.TunisieCamp.entities.CampSite;
import com.esprit.alphadev.TunisieCamp.repository.ActivityRepository;
import com.esprit.alphadev.TunisieCamp.repository.CampSiteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImp implements ActivityService {
    @Autowired
    CampSiteRepository campSiteRepository;
    @Autowired
    ActivityRepository activityRepository;


    @Override

    public void addActivityToCampsite(Integer campsiteId, @NotNull Activity activity) {
        // Retrieve the campsite by its ID
        CampSite campsite = campSiteRepository.findById(campsiteId)
                .orElseThrow(() -> new IllegalArgumentException("Campsite not found"));
        activity.setCampsite(campsite);
        activityRepository.save(activity);

    }

    @Override
    public List<Activity> getActivitiesByCampsiteName(String campsiteName) {
        // Retrieve the campsite by its name
        Optional<CampSite> optionalCampsite = Optional.ofNullable(campSiteRepository.findByName(campsiteName));

        if (optionalCampsite.isPresent()) {
            CampSite campsite = optionalCampsite.get();
            // Return the list of activities associated with the campsite
            return campsite.getActivities();
        } else {
            throw new IllegalArgumentException("Campsite not found");
        }
    }

    @Override
    public void updateActivity(Integer id, Activity activity) {
        // Retrieve the existing activity from the database
        Activity existingActivity = activityRepository.findById(id).orElse(null);

        // Update the properties of the existing activity with the new values
        existingActivity.setName(activity.getName());
        existingActivity.setDescription(activity.getDescription());
        existingActivity.setDateActivity(activity.getDateActivity());
        existingActivity.setDuration(activity.getDuration());

        // Save the updated activity
        activityRepository.save(existingActivity);
    }

    @Override
    public void deleteActivity(Integer id) {
        // Check if the activity exists
        Activity existingActivity = activityRepository.findById(id).orElse(null);
        if (existingActivity == null) {
            throw new IllegalArgumentException("Activity not found with id: " + id);
        }

        // Delete the activity
        activityRepository.delete(existingActivity);
    }


}
