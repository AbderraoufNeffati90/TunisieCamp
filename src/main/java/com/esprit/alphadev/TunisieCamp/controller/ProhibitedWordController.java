package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.service.ProhibitedWordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProhibitedWordController {


    ProhibitedWordService prohibitedWordService;
    @PostMapping("/prohibited-words")
    public void addProhibitedWords(@RequestParam("words") String words) {
        prohibitedWordService.addProhibitedWords(words);
    }


}
