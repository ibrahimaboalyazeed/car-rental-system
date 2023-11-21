package com.global.controller;

import com.global.error.CustomResponse;
import com.global.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findCarById(@PathVariable Long id){

        return ResponseEntity.ok(new CustomResponse(carService.findById(id)));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){

        return ResponseEntity.ok(new CustomResponse(carService.findAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id){

        return ResponseEntity.ok(new CustomResponse(carService.deleteCarById(id)));
    }
}
