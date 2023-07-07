package com.esprit.alphadev.TunisieCamp.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProductDto {


    private int idProduct;
    private int reference;
    private String name;
    private String description;
    private double price;
    private boolean isProductAvailable;
}
