package com.global.repository;

import com.global.entity.Booking;

import com.global.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;


public interface BookingRepo extends JpaRepository<Booking,Long> {

    @Query("SELECT c FROM Car c WHERE c.available = true AND c.workArea = :pickUpLocation AND c.id NOT IN " +
            "(SELECT b.car.id FROM Booking b WHERE " +
            "(:startDateTime BETWEEN b.startDateTime AND b.endDateTime) " +
            "OR (:endDateTime BETWEEN b.startDateTime AND b.endDateTime)" +
            "OR (b.startDateTime BETWEEN :startDateTime AND :endDateTime) " +
            "OR (b.endDateTime BETWEEN :startDateTime AND :endDateTime))")
    List<Car> findAvailableCarsBetweenStartAndEnd(
            @Param("pickUpLocation") String pickUpLocation,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );

    @Query(value = "SELECT c FROM Car c WHERE c.id = :carId AND c.available =true AND c.id IN " +
                     "(SELECT b.car.id FROM Booking b WHERE " +
                     "(:startDateTime BETWEEN b.startDateTime AND b.endDateTime) " +
                     "OR (:endDateTime BETWEEN b.startDateTime AND b.endDateTime)" +
                     "OR (b.startDateTime BETWEEN :startDateTime AND :endDateTime) " +
                     "OR (b.endDateTime BETWEEN :startDateTime AND :endDateTime))")
    List<Car> checkCarAvailability(Long carId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}



