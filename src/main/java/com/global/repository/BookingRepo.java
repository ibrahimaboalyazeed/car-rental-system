package com.global.repository;

import com.global.entity.Booking;

import com.global.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface BookingRepo extends JpaRepository<Booking,Long> {

    @Query(value = "SELECT * FROM `booking` WHERE ( car_id = :carId and start_date BETWEEN :startDate and :endDate )" +
            "or ( car_id = :carId and end_date BETWEEN :startDate and :endDate ) ;", nativeQuery = true)
    List<Booking> findByCarIdAndStartDateAndEndDate(Long carId, LocalDate startDate, LocalDate endDate);


    @Query("SELECT c FROM Car c WHERE c.available = true AND c.id NOT IN " +
            "(SELECT b.car.id FROM Booking b WHERE " +
            "(:startDate BETWEEN b.startDate AND b.endDate) " +
            "OR (:endDate BETWEEN b.startDate AND b.endDate)" +
            "OR (b.startDate BETWEEN :startDate AND :endDate)" +
            "OR (b.endDate BETWEEN :startDate AND :endDate))")
    List<Car> findAvailableCarsBetweenStartAndEnd(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


    @Query("SELECT c FROM Car c WHERE c.available = true AND c.id IN " +
            "(SELECT b.car.id FROM Booking b WHERE " +
            "(:endDate = b.startDate) " +
            "AND (:endTime < b.startTime ))")
    List<Car> findAvailableCarsINStart(
            @Param("endDate") LocalDate endDate,
            @Param("endTime") LocalTime endTime);

    @Query("SELECT c FROM Car c WHERE c.available = true AND c.id IN " +
            "(SELECT b.car.id FROM Booking b WHERE " +
            "(:startDate = b.endDate) " +
            "AND (:startTime > b.endTime ))")
    List<Car> findAvailableCarsINEnd(
            @Param("startDate") LocalDate startDate,
            @Param("startTime") LocalTime startTime);
}



