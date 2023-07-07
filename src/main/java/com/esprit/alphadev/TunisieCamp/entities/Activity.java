package com.esprit.alphadev.TunisieCamp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_act;
    private String name;
    private String description;
    private Date dateActivity;
    private Date dateAjout;
    private Integer duration;
    private String location;


    @ManyToOne
    @JoinColumn(name = "campsite_id")
    private CampSite campsite;


}
