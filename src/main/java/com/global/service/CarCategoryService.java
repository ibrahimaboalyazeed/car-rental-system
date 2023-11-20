package com.global.service;

import com.global.entity.CarCategory;
import com.global.repository.CarCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCategoryService {
    @Autowired
    private CarCategoryRepo carCategoryRepo;


    public List<CarCategory> findAll() {
        return carCategoryRepo.findAll();
    }
    public List<CarCategory> saveAll(List<CarCategory> categories) {
        return carCategoryRepo.saveAll(categories);
    }

    public CarCategory findByCategory(String category) {
        return carCategoryRepo.findByCategory(category);
    }
}
