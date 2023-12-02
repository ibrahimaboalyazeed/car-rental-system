package com.global.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.entity.enums.Transmission;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private  String model;

    @Column(name = "model_year")
    private Integer modelYear ;

    @Column(name = "color")
    private String color;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "number_of_large_bags")
    private Integer largeBag;

    @Column(name = "number_of_small_bags")
    private Integer smallBag;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission",columnDefinition = "ENUM('Automatic', 'Manual')")
    private Transmission transmission;


    @Column(name = "licence")
    private String licence;

    @Column(name = "available")
    private boolean available;

    @Column(name = "workArea")
    private String workArea;

    @Column(name = "price_per_hour")
    private Integer pricePerHour;

    @Column(name = "price_per_day")
    private Integer pricePerDay;

    @OneToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id")
    private Owner owner;



}


