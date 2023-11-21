package com.global.service;

import com.global.entity.Car;
import com.global.entity.Category;
import com.global.entity.Owner;
import com.global.entity.enums.Transmission;
import com.global.error.CustomException;
import com.global.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OwnerService ownerService;

    public List<Car> findAll() {
        return carRepo.findAll();
    }

    public List<Car> saveAll(List<Car> cars) {
        return carRepo.saveAll(cars);
    }

    public Car findById(Long id){
        return carRepo.findById(id).orElseThrow(()-> new CustomException("This car is not found"));
    }

    public int deleteCarById(Long id) {
     findById(id);
     carRepo.deleteById(id);
     return 1;
    }

    public Car AddCar(Car car) {
        if(findCarByLicence(car.getLicence()) != null)
        {
            throw new CustomException("This car already exists");
        }
        Car carToCreate = new Car();
        carToCreate.setMake(car.getMake());
        carToCreate.setModel(car.getModel());
        carToCreate.setModelYear(car.getModelYear());
        carToCreate.setLicence(car.getLicence());
        carToCreate.setDoors(car.getDoors());
        carToCreate.setTransmission(car.getTransmission());
        carToCreate.setLargeBag(car.getLargeBag());
        carToCreate.setSmallBag(car.getSmallBag());
        carToCreate.setColor(car.getColor());
        carToCreate.setAvailable(true);
        Category category = categoryService.findByCategoryId(car.getCategory().getId());
        carToCreate.setCategory(category);
        Owner carOwner = ownerService.findById(car.getOwner().getId());
        carToCreate.setOwner(carOwner);

        return carRepo.save(carToCreate);

    }
    public Car findCarByLicence (String licence)
    {
        return carRepo.findByLicence(licence);
    }

    public List<Car> findByMake(String make) {
        return carRepo.findByMakeIgnoreCase(make);
    }

    public List<Car> findByTransmission(Transmission transmission) {
        return carRepo.findByTransmission(transmission);
    }
}
