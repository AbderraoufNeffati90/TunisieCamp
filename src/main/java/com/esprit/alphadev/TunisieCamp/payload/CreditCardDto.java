package com.esprit.alphadev.TunisieCamp.payload;

import com.esprit.alphadev.TunisieCamp.entities.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Data
public class CreditCardDto {
    private Integer idLoyaltyCard;

    private boolean isCreditCardAvailable;


    @NotEmpty
    @Valid
    @JsonIgnoreProperties("creditCard")
    private Set<OrderDto> orders;

    public void setOrders(Set<OrderDto> orders) {
        this.orders = orders;
        if (Objects.nonNull(this.orders)){
            orders.forEach(OrderDto -> {
                OrderDto.setCreditCard(this);
            });
        }
    }

}
