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
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDelivery")
    private Integer idDelivery;

    @Column(name = "deliveryDate")
    private LocalDate deliveryDate;
    @Column(name = "deliveryAddress")

    private String deliveryAddress;

    @OneToOne
    @JoinColumn(name = "factureDeliveryId")
    private Facture facture;
}
