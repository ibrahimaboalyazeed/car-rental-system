package com.global.service;

import com.global.entity.Location;
import com.global.error.CustomException;
import com.global.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepo locationRepo;

    public Location findLocation(Long locationId){

        return locationRepo.findById(locationId).orElseThrow(() -> new CustomException("This Location does not exist"));
    }




}
