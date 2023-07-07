package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Activity;
import com.esprit.alphadev.TunisieCamp.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/activities")

public class ActivityController {
    ActivityService activityService;

    @PostMapping("/{campsiteId}/activities")
    public ResponseEntity<Void> addActivityToCampsite(@PathVariable Integer campsiteId, @RequestBody Activity activity) {
        activityService.addActivityToCampsite(campsiteId, activity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{campsiteName}/activities")
    public ResponseEntity<List<Activity>> getActivitiesByCampsiteName(@PathVariable String campsiteName) {
        try {
            List<Activity> activities = activityService.getActivitiesByCampsiteName(campsiteName);
            return ResponseEntity.ok(activities);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateActivity(@PathVariable Integer id, @RequestBody Activity activity) {
        activityService.updateActivity(id, activity);
        return ResponseEntity.ok("Activity updated successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Integer id) {
        activityService.deleteActivity(id);
        return ResponseEntity.ok("Activity deleted successfully");
    }

}
