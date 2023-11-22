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
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllCategories() {

        return ResponseEntity.ok(new CustomResponse(categoryService.findAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CustomResponse(categoryService.findByCategoryId(id)));
    }
    @GetMapping("/name")
    public ResponseEntity<?> findCategoryByName(@RequestParam String name) {

        return ResponseEntity.ok(new CustomResponse(categoryService.findByCategoryName(name)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CustomResponse(categoryService.deleteCategoryById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category carCategory) {
        return ResponseEntity.ok(categoryService.createCategory(carCategory));
    }

    @PutMapping ("/update")
    public ResponseEntity<?> UpdateCategory(@RequestBody Category carCategory) {
        return ResponseEntity.ok(categoryService.updateCategory(carCategory));
    }

    }
