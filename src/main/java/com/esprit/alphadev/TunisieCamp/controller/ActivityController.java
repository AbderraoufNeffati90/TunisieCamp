package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Activity;
import com.esprit.alphadev.TunisieCamp.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/activities")

public class ActivityController {
    ActivityService activityService;

    @PostMapping("/{campsiteId}/activities")
    public ResponseEntity<Void> addActivityToCampsite(@PathVariable Long campsiteId, @RequestBody Activity activity) {
        activityService.addActivityToCampsite(campsiteId, activity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{campsiteName}/activities")
    public ResponseEntity<List<Activity>> getActivitiesByCampsiteName(@PathVariable String name) {
        try {
            List<Activity> activities = activityService.getActivitiesByCampsiteName(name);
            return ResponseEntity.ok(activities);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
