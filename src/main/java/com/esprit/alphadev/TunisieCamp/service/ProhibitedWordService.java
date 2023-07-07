package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.ProhibitedWord;

import java.util.List;

public interface ProhibitedWordService {
    void addProhibitedWords(String words);

    List<ProhibitedWord> getAllProhibitedWords();
}
