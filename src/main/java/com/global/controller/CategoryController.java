package com.global.controller;

import com.global.entity.Category;
import com.global.error.CustomResponse;
import com.global.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService carCategoryService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllCategories() {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.findAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.findByCategoryId(id)));
    }
    @GetMapping("/name")
    public ResponseEntity<?> findCategoryByName(@RequestParam String name) {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.findByCategoryName(name)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CustomResponse(carCategoryService.deleteCategoryById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category carCategory) {
        return ResponseEntity.ok(carCategoryService.createCategory(carCategory));
    }

    @PutMapping ("/update")
    public ResponseEntity<?> UpdateCategory(@RequestBody Category carCategory) {
        return ResponseEntity.ok(carCategoryService.updateCategory(carCategory));
    }

    }
