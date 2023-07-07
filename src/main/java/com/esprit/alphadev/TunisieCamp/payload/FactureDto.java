package com.esprit.alphadev.TunisieCamp.payload;

import com.esprit.alphadev.TunisieCamp.entities.Order;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data

public class FactureDto {

    private int idFacture;
    private LocalDate dateFacture;
    private double totalFacture;
    private boolean isFactureAvailable;
    private OrderDto order;
}
