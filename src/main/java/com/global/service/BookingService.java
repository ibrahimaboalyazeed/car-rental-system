package com.global.service;

import com.global.entity.Booking;
import com.global.error.CustomException;
import com.global.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepo bookingRepo;

    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    public Booking findById(Long id) {
        return bookingRepo.findById(id).orElseThrow(()-> new CustomException("This Booking is not found"));
    }
}
