package com.global.repository;

import com.global.entity.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarOwnerRepo extends JpaRepository<CarOwner,Long> {
}
