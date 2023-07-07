package com.esprit.alphadev.TunisieCamp.entities;

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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrderItem")
    private Integer idOrderItem;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "totalOrderItem")
    private Double totalOrderItem;
    @Column(name = "isOrderItemAvailable")
    private boolean isOrderItemAvailable;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    //  @JsonBackReference
    private Order order;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

}
