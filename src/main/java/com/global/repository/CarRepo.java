package com.global.repository;

import com.global.entity.Car;
import com.global.entity.enums.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,Long> {
    Car findByLicence(String licence);

    List<Car> findByMakeIgnoreCase(String make);

    List<Car> findByTransmission(Transmission transmission);

    List<Car> findByCategoryId(Long id);
}
