package com.esprit.alphadev.TunisieCamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder")
    private Integer idOrder;
    @Column(name = "creationDate")
    private LocalDate creationDate;
    @Column(name = "total")
    private double totalOrder;
    @Column(name = "isOrderAvailable")
    private boolean isOrderAvailable;

    @OneToOne
    @JoinColumn(name = "factureId")
    private Facture facture;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("order")
    private List<OrderItem> orderItems;


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditCardId", nullable = false)
    //  @JsonBackReference
    private CreditCard creditCard;
}
