package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Feedback;
import com.esprit.alphadev.TunisieCamp.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {

    FeedbackService feedbackService;

    @PostMapping("/add/{userId}/{campingCenterId}")
    public ResponseEntity<String>AddFeedback (
            @RequestBody Feedback feedback,
            @PathVariable("userId") Long userId,
            @PathVariable("campingCenterId") Long campingCenterId) {

        try {
            feedbackService.AddFeedback(feedback, userId, campingCenterId);
            return ResponseEntity.ok("Feedback added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }
    @GetMapping("/with-users")
    public List<Feedback> getAllFeedbacksWithUsers() {
        return feedbackService.getAllFeedbacksWithUsers();
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateFeedback(@PathVariable("id") Long feedbackId, @RequestBody Feedback updatedFeedback) {
        try {
            // Set the ID of the updated feedback
            updatedFeedback.setIdFeed(feedbackId);

            // Update the feedback
            feedbackService.UpdateFeedback(updatedFeedback);

            return ResponseEntity.ok().body("Feedback updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update feedback");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") Long feedbackId) {
        try {
            feedbackService.deleteFeedback(feedbackId);
            return ResponseEntity.ok().body("Feedback deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{name}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable String name) {
        double averageRating = feedbackService.calculateAverageRating(name);
        return ResponseEntity.ok(averageRating);
    }


}

