package com.esprit.alphadev.TunisieCamp.repository;

import com.esprit.alphadev.TunisieCamp.entities.CreditCard;
import com.esprit.alphadev.TunisieCamp.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository  extends JpaRepository<CreditCard, Integer> {
}
