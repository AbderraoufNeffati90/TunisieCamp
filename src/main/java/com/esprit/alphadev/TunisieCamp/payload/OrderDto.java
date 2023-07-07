package com.esprit.alphadev.TunisieCamp.payload;

import com.esprit.alphadev.TunisieCamp.entities.CreditCard;
import com.esprit.alphadev.TunisieCamp.entities.Facture;
import com.esprit.alphadev.TunisieCamp.entities.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data

public class OrderDto {

    private Integer idOrder;
    private LocalDate creationDate;
    private Double totalOrder;
    private boolean isOrderAvailable;
    private FactureDto facture;


    @NotEmpty
    @Valid
    @JsonIgnoreProperties("order")
    private List<OrderItemDto> orderItems;

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;

        if (Objects.nonNull(this.orderItems)){
            orderItems.forEach(OrderItemDto -> {
                OrderItemDto.setOrder(this);
            });
        }
    }

    @NotNull
    //  @JsonBackReference
    private CreditCardDto creditCard;


}
