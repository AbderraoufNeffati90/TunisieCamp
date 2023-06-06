package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Feedback;

import java.util.List;

public interface FeedbackService {

    void AddFeedback(Feedback feedback, Long id, Long campingCenterId);

    void UpdateFeedback(Feedback updatedFeedback);

    void deleteFeedback(Long feedbackId);

    List<Feedback> getAllFeedbacks();

    List<Feedback> getAllFeedbacksWithUsers();

    double calculateAverageRating(String name);
}
