package com.global.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private int id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private  String model;

    @Column(name = "year")
    private int year ;

    @Column(name = "color")
    private String color;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "seats")
    private int seats;

    @Column(name = "doors")
    private int doors;

    @Column(name = "licence")
    private String licence;

    @Column(name = "available")
    private boolean available;

    @Column(name = "price_per_hour")
    private int pricePerHour;

    @Column(name = "price_per_day")
    private int pricePerDay;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id")
    private CarOwner carOwner;


}
