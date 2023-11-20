package com.global.controller;

import com.global.error.CustomResponse;
import com.global.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("/address/{locationId}")
    public ResponseEntity<?> findLocation(@PathVariable Long locationId){
        return  ResponseEntity.ok( new CustomResponse(locationService.findLocation(locationId)));
    }
}
