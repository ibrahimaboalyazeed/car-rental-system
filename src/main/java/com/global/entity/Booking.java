package com.global.entity;

import com.global.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Booking")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="booking_id")
    private  Long id;

    @Column(name ="start_date")
    private LocalDate startDate;

    @Column(name ="start_time")
    private LocalTime startTime;

    @Column(name ="end_date")
    private LocalDate endDate;

    @Column(name ="end_time")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",columnDefinition = "ENUM ('ACTIVE','INACTIVE','PENDING')")
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "location_id",nullable = false)
    private  Location Location;

    @ManyToOne()
    @JoinColumn(name = "car_id",nullable = false)
    private Car car;
}
