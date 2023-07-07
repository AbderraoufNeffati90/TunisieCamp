package com.esprit.alphadev.TunisieCamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Boolean confirmed;
    private Integer numberOfPeople;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "User_id")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "center_id")
    private CampingCenter campingCenter;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "campsite_id")
    private CampSite campsite;

}
