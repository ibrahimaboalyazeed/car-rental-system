package com.global.service;

import com.global.entity.CarOwner;
import com.global.error.CustomException;
import com.global.repository.CarOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarOwnerService {
    @Autowired
    private CarOwnerRepo carOwnerRepo;


    public List<CarOwner> findAll(){
        return carOwnerRepo.findAll();
    }

    public List<CarOwner> saveAll(List<CarOwner> carOwners) {

        return carOwnerRepo.saveAll(carOwners);
    }

    public  CarOwner findById (Long id)
    {
        return carOwnerRepo.findById(id).orElseThrow(() -> new CustomException("This Owner does not exist"));
    }
}
