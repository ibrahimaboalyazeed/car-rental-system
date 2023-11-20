package com.global.repository;

import com.global.entity.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarCategoryRepo extends JpaRepository<CarCategory,Long> {

    CarCategory findByCategory(String category);
}
