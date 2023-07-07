package com.esprit.alphadev.TunisieCamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCreditCard")
    private Integer idLoyaltyCard;

    @Column(name = "isCreditCardAvailable")
    private boolean isCreditCardAvailable;


    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL, orphanRemoval = false,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("creditCard")
    private Set<Order> orders;


    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
