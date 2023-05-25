package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.Feedback;
import com.esprit.alphadev.TunisieCamp.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {

    FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<String> AddFeedback(@RequestBody Feedback feedback) {
        feedbackService.AddFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully.");
    }
    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }
    @GetMapping("/with-users")
    public List<Feedback> getAllFeedbacksWithUsers() {
        return feedbackService.getAllFeedbacksWithUsers();
    }
}
