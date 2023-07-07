package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Activity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ActivityService {

    void addActivityToCampsite(Integer campsiteId, @NotNull Activity activity);



    List<Activity> getActivitiesByCampsiteName(String campsiteName);


    void updateActivity(Integer id, Activity activity);

    void deleteActivity(Integer id);
}
