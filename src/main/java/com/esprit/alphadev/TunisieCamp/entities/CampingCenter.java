package com.esprit.alphadev.TunisieCamp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CampingCenter {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idCentre;
        private String name;
        private String address;
        private Integer phoneNumber;
        @Email
        private String email;

        @OneToMany(mappedBy = "campingCenter")
        private List<CampSite> campsites;

        @OneToMany(mappedBy = "campingCenter")
        private List<Feedback> feedbacks;

        @OneToMany(mappedBy = "campingCenter")
        private List<Reservation> reservations;


        @ManyToOne
        @JoinColumn(name = "id")
        private User user;



}
