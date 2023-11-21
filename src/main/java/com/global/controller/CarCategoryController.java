package com.global.controller;

import com.global.entity.CarCategory;
import com.global.error.CustomResponse;
import com.global.service.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CarCategoryController {

    @Autowired
    private CarCategoryService carCategoryService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllCategories() {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.findAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.findByCategoryId(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.deleteCategoryById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CarCategory carCategory) {
        return ResponseEntity.ok(carCategoryService.createCategory(carCategory));
    }

    @PutMapping ("/update")
    public ResponseEntity<?> UpdateCategory(@RequestBody CarCategory carCategory) {
        return ResponseEntity.ok(carCategoryService.updateCategory(carCategory));
    }

    }
