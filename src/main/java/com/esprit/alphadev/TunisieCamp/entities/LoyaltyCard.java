package com.esprit.alphadev.TunisieCamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoyaltyCard")
    private Integer idLoyaltyCard;

    @Column(name = "isLoyaltyCardAvailable")
    private boolean isLoyaltyCardAvailable;


    @OneToMany(mappedBy = "loyaltyCard", cascade = CascadeType.ALL, orphanRemoval = false,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("loyaltyCard")
    private List<Order> orders;


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
