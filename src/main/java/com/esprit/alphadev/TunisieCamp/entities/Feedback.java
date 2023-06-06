package com.esprit.alphadev.TunisieCamp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeed;
    @Min(value = 0)
    @Max(value = 5)
    private Integer rating;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDate dateAjout;
    @Column(nullable = false)
    private LocalDate dateUpdate;



    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idCenter")
    private CampingCenter campingCenter;


    @PrePersist
    protected void onCreate() {
        dateAjout = LocalDate.now();
        dateUpdate= LocalDate.now();
    }


}
