package com.global.controller;

import com.global.entity.Car;
import com.global.entity.enums.Transmission;
import com.global.error.CustomResponse;
import com.global.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @GetMapping("/featured")
    public ResponseEntity<?> findFeaturedCars(){
        return ResponseEntity.ok(new CustomResponse(carService.findFeaturedCars()));
    }
    @GetMapping("/make")
    public ResponseEntity<?> findByMake(@RequestParam String make){

        return ResponseEntity.ok(new CustomResponse(carService.findByMake(make)));
    }
    @GetMapping("/transmission")
    public ResponseEntity<?> findByMake(@RequestParam Transmission transmission){

        return ResponseEntity.ok(new CustomResponse(carService.findByTransmission(transmission)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id){

        return ResponseEntity.ok(new CustomResponse(carService.deleteCarById(id)));
    }
    @PostMapping("/add")
    public ResponseEntity<?> AddCar(@RequestBody Car car)
    {
        return ResponseEntity.ok(new CustomResponse(carService.AddCar(car)));
    }
}
