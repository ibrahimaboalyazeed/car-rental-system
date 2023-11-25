package com.global.controller;

import com.global.entity.Booking;
import com.global.error.CustomResponse;
import com.global.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

     @GetMapping("/all")
    public ResponseEntity<?> findAll()
     {
         return ResponseEntity.ok(new CustomResponse(bookingService.findAll()));
     }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(new CustomResponse(bookingService.findById(id)));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking){
         return ResponseEntity.ok(new CustomResponse(bookingService.addBooking(booking)));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id)
    {
        return ResponseEntity.ok(new CustomResponse(bookingService.deleteById(id)));
    }
    @GetMapping("/available-cars")
    public ResponseEntity<?> findAvailableCars(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate ,
                                               @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate ,
                                               @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime endTime )
    {
        return ResponseEntity.ok(new CustomResponse(bookingService.findAvailableCars(startDate,startTime,endDate,endTime)));
    }
}
