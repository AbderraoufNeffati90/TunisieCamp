package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Feedback;

import java.util.List;

public interface FeedbackService {


    void AddFeedback(Feedback feedback);

    List<Feedback> getAllFeedbacks();

    List<Feedback> getAllFeedbacksWithUsers();
}
