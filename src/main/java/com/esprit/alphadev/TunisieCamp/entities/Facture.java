package com.esprit.alphadev.TunisieCamp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFacture")
    private Integer idFacture;
    @Column(name = "dateFacture")
    private LocalDate dateFacture;
    @Column(name = "totalFacture")
    private Double totalFacture;
    @Column(name = "isFactureAvailable")
    private boolean isFactureAvailable;
    @OneToOne(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;



}
