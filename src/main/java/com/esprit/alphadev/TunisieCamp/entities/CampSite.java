package com.esprit.alphadev.TunisieCamp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Activity> activities;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "camping_center_id")
 //   @JsonBackReference

    private CampingCenter campingCenter;

    @OneToMany(mappedBy = "campsite")
    @JsonIgnore
    private List<Reservation> reservations;


    public boolean isAvailable() {
        return isAvailable;

    }
}
