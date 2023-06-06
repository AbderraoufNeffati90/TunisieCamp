package com.esprit.alphadev.TunisieCamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        private String phoneNumber;
        @Email
        private String email;

        @OneToMany(mappedBy = "campingCenter")
        @JsonIgnore
        private List<CampSite> campsites;

        @OneToMany(mappedBy = "campingCenter")
        @JsonIgnore
        private List<Feedback> feedbacks;

        @OneToMany(mappedBy = "campingCenter")
        @JsonIgnore
        private List<Reservation> reservations;


        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "id")
        private User user;



}
