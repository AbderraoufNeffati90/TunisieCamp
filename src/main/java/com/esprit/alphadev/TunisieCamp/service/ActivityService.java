package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Activity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ActivityService {

    void addActivityToCampsite(Long campsiteId, @NotNull Activity activity);

    List<Activity> getActivitiesByCampsiteId(Long campsiteId);
}
