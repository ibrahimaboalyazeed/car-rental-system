package com.global.service;

import com.global.entity.Car;
import com.global.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;

    public  List<Car> findAll(){
        return carRepo.findAll();
    }
    public  List<Car> saveAll(List<Car> cars){
        return carRepo.saveAll(cars);
    }


}
