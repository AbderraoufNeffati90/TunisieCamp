package com.esprit.alphadev.TunisieCamp.controller;

import com.esprit.alphadev.TunisieCamp.entities.ProhibitedWord;
import com.esprit.alphadev.TunisieCamp.service.ProhibitedWordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/prohibited-words")
public class ProhibitedWordController {


    ProhibitedWordService prohibitedWordService;
    @PostMapping("/add")
    public void addProhibitedWords(@RequestParam("words") String words) {
        prohibitedWordService.addProhibitedWords(words);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProhibitedWord>> getAllProhibitedWords() {
        List<ProhibitedWord> prohibitedWords = prohibitedWordService.getAllProhibitedWords();
        return ResponseEntity.ok(prohibitedWords);
    }


}
