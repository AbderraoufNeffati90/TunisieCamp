package com.esprit.alphadev.TunisieCamp.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstname;
    private String lastname ;
    @Email
    private String email ;
    private  String password;

    @Enumerated(EnumType.STRING)
    private Role role ;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private List<CampingCenter> campingCenters;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
