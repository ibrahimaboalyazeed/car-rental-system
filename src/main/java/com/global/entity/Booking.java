package com.global.entity;

import com.global.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name ="start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @Column(name ="end_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;



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
