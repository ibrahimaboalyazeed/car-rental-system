package com.global.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_owner")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "driving_licence")
    private String drivingLicence;

    @Column(name = "contact_number")
    private String contactNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "carOwner")
    private List<Car> car ;

}
