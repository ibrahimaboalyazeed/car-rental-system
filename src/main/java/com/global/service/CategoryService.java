package com.global.service;

import com.global.entity.Category;
import com.global.error.CustomException;
import com.global.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;


    public List<Category> findAllCategories() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return categoryRepo.findAll(sort);
    }

    public List<Category> saveAll(List<Category> categories) {
        return categoryRepo.saveAll(categories);
    }

    public Category findByCategoryName(String category) {
        return categoryRepo.findByCategoryIgnoreCase(category);
    }


    public Category findByCategoryId(Long id) {

        return categoryRepo.findById(id).orElseThrow(() -> new CustomException("This category is not found"));
    }

    public int deleteCategoryById(Long id) {

        findByCategoryId(id);
        categoryRepo.deleteById(id);
        return 1;
    }

    public Category createCategory(Category carCategory) {

        if(findByCategoryName(carCategory.getCategory()) != null){
            throw  new CustomException("This category is already exists");
        }
        Category carCategory1 = new Category();
        carCategory1.setCategory(carCategory.getCategory());
        return  categoryRepo.save(carCategory1);
    }

    public Category updateCategory(Category carCategory) {

        Category carCategory1 = findByCategoryId(carCategory.getId());
       if(findByCategoryName(carCategory.getCategory()) != null){
           if(findByCategoryName(carCategory.getCategory()).getId() != carCategory.getId())
           {
               throw  new CustomException("This category is already exists");
           }
       }
        carCategory1.setCategory(carCategory.getCategory());
        return  categoryRepo.save(carCategory1);


    }
}
