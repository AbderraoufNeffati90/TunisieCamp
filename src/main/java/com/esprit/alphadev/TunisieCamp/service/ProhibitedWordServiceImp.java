package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.ProhibitedWord;
import com.esprit.alphadev.TunisieCamp.repository.ProhibitedWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProhibitedWordServiceImp implements ProhibitedWordService{

    @Autowired
    ProhibitedWordRepository prohibitedWordRepository;


    @Override
    public void addProhibitedWords(String words) {
        String[] wordArray = words.split(",");
        List<ProhibitedWord> prohibitedWords = new ArrayList<>();
        for (String word : wordArray) {
            ProhibitedWord prohibitedWord = new ProhibitedWord();
            prohibitedWord.setWord(word.trim());
            prohibitedWords.add(prohibitedWord);
        }
        prohibitedWordRepository.saveAll(prohibitedWords);
    }

}
