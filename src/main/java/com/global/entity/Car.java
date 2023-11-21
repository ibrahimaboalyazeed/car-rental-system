package com.global.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.entity.enums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private int modelYear ;

    @Column(name = "color")
    private String color;

    @Column(name = "doors")
    private int doors;

    @Column(name = "number_of_large_bags")
    private int largeBag;

    @Column(name = "number_of_small_bags")
    private int smallBag;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission",columnDefinition = "ENUM('Automatic', 'Manual')")
    private Transmission transmission;


    @Column(name = "licence")
    private String licence;

    @Column(name = "available")
    private boolean available;

    @Column(name = "price_per_hour")
    private int pricePerHour;

    @Column(name = "price_per_day")
    private int pricePerDay;

    @OneToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CarCategory carCategory;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id")
    private CarOwner carOwner;


}
