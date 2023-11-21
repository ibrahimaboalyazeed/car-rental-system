package com.global.service;

import com.global.entity.CarCategory;
import com.global.error.CustomException;
import com.global.repository.CarCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCategoryService {
    @Autowired
    private CarCategoryRepo carCategoryRepo;


    public List<CarCategory> findAllCategories() {
        return carCategoryRepo.findAll();
    }

    public List<CarCategory> saveAll(List<CarCategory> categories) {
        return carCategoryRepo.saveAll(categories);
    }

    public CarCategory findByCategoryName(String category) {
        return carCategoryRepo.findByCategory(category);
    }


    public CarCategory findByCategoryId(Long id) {

        return carCategoryRepo.findById(id).orElseThrow(() -> new CustomException("This category is not found"));
    }

    public int deleteCategoryById(Long id) {

        findByCategoryId(id);
        carCategoryRepo.deleteById(id);
        return 1;
    }

    public CarCategory createCategory(CarCategory carCategory) {

        if(findByCategoryName(carCategory.getCategory()) != null){
            throw  new CustomException("This category is already exists");
        }
        CarCategory carCategory1 = new CarCategory();
        carCategory1.setCategory(carCategory.getCategory());
        return  carCategoryRepo.save(carCategory1);
    }

    public CarCategory updateCategory(CarCategory carCategory) {

        CarCategory carCategory1 = findByCategoryId(carCategory.getId());
        if(findByCategoryName(carCategory.getCategory()) != null){
            throw  new CustomException("This category is already exists");
        }
        carCategory1.setCategory(carCategory.getCategory());
        return  carCategoryRepo.save(carCategory1);


    }
}
