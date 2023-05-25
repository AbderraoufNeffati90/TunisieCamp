package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.Feedback;
import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FeedbackServiceImp implements FeedbackService{

    FeedbackRepository feedbackRepository;
    @Override
    public void AddFeedback(Feedback feedback) {
        Feedback newFeedback = new Feedback();
        newFeedback.setRating(feedback.getRating());

        String sanitizedComment = sanitizeComment(feedback.getComment());
        feedback.setComment(sanitizedComment);

        feedbackRepository.save(feedback);
    }

    private String sanitizeComment(String comment) {
        List<String> prohibitedWords = readProhibitedWordsFromFile();
        return prohibitedWords.stream()
                .reduce(comment, (c, word) -> c.replaceAll(word, "*".repeat(word.length())));
    }

    private List<String> readProhibitedWordsFromFile() {
        List<String> prohibitedWords = new ArrayList<>();
        try {
            File file = new File("src/main/java/prohibited_words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                prohibitedWords.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle the case when the file is not found
        }
        return prohibitedWords;
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
}
