package com.esprit.alphadev.TunisieCamp.payload;

import com.esprit.alphadev.TunisieCamp.entities.Facture;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
public class DeliveryDto {


    private int idDelivery;

    private LocalDate deliveryDate;

    private String deliveryAddress;

    private FactureDto facture;
}
