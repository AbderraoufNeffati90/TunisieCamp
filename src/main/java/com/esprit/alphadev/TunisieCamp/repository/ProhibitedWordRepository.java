package com.esprit.alphadev.TunisieCamp.repository;

import com.esprit.alphadev.TunisieCamp.entities.ProhibitedWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProhibitedWordRepository extends JpaRepository<ProhibitedWord, Long> {
}
