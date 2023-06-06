package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.CampingCenter;
import com.esprit.alphadev.TunisieCamp.entities.Feedback;
import com.esprit.alphadev.TunisieCamp.entities.ProhibitedWord;
import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.CampingCenterRepository;
import com.esprit.alphadev.TunisieCamp.repository.FeedbackRepository;
import com.esprit.alphadev.TunisieCamp.repository.ProhibitedWordRepository;
import com.esprit.alphadev.TunisieCamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImp implements FeedbackService{

    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CampingCenterRepository campingCenterRepository;
    @Autowired
    ProhibitedWordRepository prohibitedWordRepository;
    @Override
    public void AddFeedback(Feedback feedback, Long id, Long campingCenterId) {

        Optional<User> user = userRepository.findById(id);
        Optional<CampingCenter> campingCenter = campingCenterRepository.findById(campingCenterId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }

        if (campingCenter == null) {
            throw new IllegalArgumentException("Camping center not found with ID: " + campingCenterId);
        }


        Feedback newFeedback = new Feedback();
        newFeedback.setRating(feedback.getRating());

        String sanitizedComment = sanitizeComment(feedback.getComment());
        feedback.setComment(sanitizedComment);
        // Set the current date
        feedback.setDateAjout(LocalDate.now());

        feedbackRepository.save(feedback);
    }

    private String sanitizeComment(String comment) {
        List<String> prohibitedWords = getProhibitedWords();
        return prohibitedWords.stream()
                .reduce(comment, (c, word) -> c.replaceAll(word, "*".repeat(word.length())));
    }

    private List<String> getProhibitedWords() {
        List<String> prohibitedWords = new ArrayList<>();
        List<ProhibitedWord> prohibitedWordEntities = prohibitedWordRepository.findAll();
        for (ProhibitedWord prohibitedWord : prohibitedWordEntities) {
            prohibitedWords.add(prohibitedWord.getWord());
        }
        return prohibitedWords;
    }


    @Override
    public void UpdateFeedback(Feedback updatedFeedback) {
        // Retrieve the existing feedback by its ID
        Feedback existingFeedback = feedbackRepository.findById(updatedFeedback.getIdFeed())
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found with ID: " + updatedFeedback.getIdFeed()));

        // Update the properties of the existing feedback
        existingFeedback.setRating(updatedFeedback.getRating());
        String sanitizedComment = sanitizeComment(updatedFeedback.getComment());
        existingFeedback.setComment(sanitizedComment);

        updatedFeedback.setDateUpdate(LocalDate.now());

        // Save the updated feedback
        feedbackRepository.save(existingFeedback);
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found with ID: " + feedbackId));

        feedbackRepository.delete(feedback);
    }


@Override
    public List<Feedback> getAllFeedbacks() {
        // Retrieve all feedbacks from the database
        return feedbackRepository.findAll();
    }
    @Override
    public List<Feedback> getAllFeedbacksWithUsers() {
        // Retrieve all feedbacks from the database along with the associated users
        List<Feedback> feedbacks = feedbackRepository.findAll();
        for (Feedback feedback : feedbacks) {
            User user = feedback.getUser();
            user.setFeedbacks(null);
        }
        return feedbacks;
    }

    @Override
    public double calculateAverageRating(String name) {
        List<Feedback> feedbackList = feedbackRepository.findByCampingCenterNameLike(name);
        if (feedbackList.isEmpty()) {
            return 0.0;
        }
        int totalRating = feedbackList.stream().mapToInt(Feedback::getRating).sum();
        return (double) totalRating / feedbackList.size();
    }
}
