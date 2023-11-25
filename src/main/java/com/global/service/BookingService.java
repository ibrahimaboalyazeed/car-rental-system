package com.global.service;

import com.global.entity.*;
import com.global.entity.enums.Transmission;
import com.global.error.CustomException;
import com.global.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private CarService carService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OwnerService ownerService;

    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    public Booking findById(Long id) {
        return bookingRepo.findById(id).orElseThrow(() -> new CustomException("This Booking is not found"));
    }

    public Booking addBooking(Booking booking) {

        validateBookingStartDateAndTime(booking.getStartDate(), booking.getStartTime(), booking.getEndDate());
        Car car = carService.findById(booking.getCar().getId());
        //checkCarAvailability(car.getId(), booking.getStartDate(), booking.getEndDate());
        Client client = clientService.findById(booking.getClient().getId());

        Location location = new Location();
        location.setName(booking.getLocation().getName());
        location.setCountry(booking.getLocation().getCountry());
        location.setCity(booking.getLocation().getCity());
        location.setStreet(booking.getLocation().getStreet());

        Booking bookingToInsert = new Booking();
        bookingToInsert.setStartTime(booking.getStartTime());
        bookingToInsert.setStartDate(booking.getStartDate());
        bookingToInsert.setEndTime(booking.getEndTime());
        bookingToInsert.setEndDate(booking.getEndDate());
        bookingToInsert.setCar(car);
        bookingToInsert.setClient(client);
        bookingToInsert.setLocation(location);

        return bookingRepo.save(bookingToInsert);

    }

    public void validateBookingStartDateAndTime(LocalDate startDate, LocalTime startTime, LocalDate endDate) {
        if (startDate.isBefore(LocalDate.now())
                || (startDate.isEqual(LocalDate.now()) && startTime.isBefore(LocalTime.now()))) {
            throw new CustomException("Booking date cannot be in the past.");
        }
        if (endDate.isBefore(LocalDate.now())) {
            throw new CustomException("End date can not be in the past");
        }
        if (endDate.isBefore(startDate)) {
            throw new CustomException("End date must be after start date");
        }

    }

    public boolean checkCarAvailability(Long carId, LocalDate startDate, LocalDate endDate) {

        if (bookingRepo.findByCarIdAndStartDateAndEndDate(carId, startDate, endDate).isEmpty()) {
            return true;
        }
        throw new CustomException("the car is already reserved");
    }

    public String deleteById(Long id) {
        Booking booking = findById(id);
        bookingRepo.deleteById(id);
        return "Deleted";
    }


    public List<Car> findAvailableCars(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {

        validateBookingStartDateAndTime(startDate, startTime, endDate);
        //System.out.println("555555555555555555555555555555555555555555555555"+startDate);
        //System.out.println("6666666666666666666666666666666666666666666666666"+endDate);
        List<Car> cars = bookingRepo.findAvailableCarsBetweenStartAndEnd(startDate, endDate);
        List<Car>  cars1 = bookingRepo.findAvailableCarsINStart(endDate,endTime);
        List<Car>  cars2 = bookingRepo.findAvailableCarsINEnd(startDate,startTime);
        cars.addAll(cars1);
        cars.addAll(cars2);

        return cars;
    }
}
