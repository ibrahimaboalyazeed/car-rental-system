package com.global.repository;

import com.global.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    Category findByCategoryIgnoreCase(String category);
}
