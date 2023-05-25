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
public class CampSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private String address;
    private String description;
    private Integer capacity;
    private Double pricePerNight;
    private Boolean isAvailable;


    @OneToMany(mappedBy = "campsite")
    private List<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "camping_center_id")
    private CampingCenter campingCenter;
    @OneToMany(mappedBy = "campsite")
    private List<Reservation> reservations;


}
