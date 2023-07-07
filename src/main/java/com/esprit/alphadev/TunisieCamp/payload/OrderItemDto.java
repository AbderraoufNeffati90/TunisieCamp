package com.esprit.alphadev.TunisieCamp.payload;

import com.esprit.alphadev.TunisieCamp.entities.Order;
import com.esprit.alphadev.TunisieCamp.entities.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data

public class OrderItemDto {



    private int idOrderItem;

    private int quantity;
    private double totalOrderItem;
    private boolean isOrderItemAvailable;


    @NotNull
    //  @JsonBackReference
    private OrderDto order;
    @NotNull
    //  @JsonBackReference
    private ProductDto product;
}
