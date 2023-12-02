package com.global.service;

import com.global.entity.*;
import com.global.error.CustomException;
import com.global.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        validateBookingStartDateAndTime(booking.getStartDateTime(),booking.getEndDateTime());
        Car car = carService.findById(booking.getCar().getId());
        checkCarAvailability(car.getId(), booking.getStartDateTime(), booking.getEndDateTime());
        Client client = clientService.findById(booking.getClient().getId());

        Location location = new Location();
        location.setName(booking.getLocation().getName());
        location.setCountry(booking.getLocation().getCountry());
        location.setCity(booking.getLocation().getCity());
        location.setStreet(booking.getLocation().getStreet());

        Booking bookingToInsert = new Booking();
        bookingToInsert.setStartDateTime(booking.getStartDateTime());
        bookingToInsert.setEndDateTime(booking.getEndDateTime());
        bookingToInsert.setCar(car);
        bookingToInsert.setClient(client);
        bookingToInsert.setLocation(location);

        return bookingRepo.save(bookingToInsert);

    }

    public void validateBookingStartDateAndTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isBefore(LocalDateTime.now()))
        {
            throw new CustomException("Booking date cannot be in the past.");
        }
        if (endDateTime.isBefore(LocalDateTime.now())) {
            throw new CustomException("End date can not be in the past");
        }
        if (endDateTime.isBefore(startDateTime)) {
            throw new CustomException("End date must be after start date");
        }

    }

    public boolean checkCarAvailability(Long carId, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        if (bookingRepo.checkCarAvailability(carId, startDateTime, endDateTime).isEmpty()) {
            return true;
        }
        throw new CustomException("the car is already reserved");
    }

    public String deleteById(Long id) {
        Booking booking = findById(id);
        bookingRepo.deleteById(id);
        return "Deleted";
    }


    public List<Car> findAvailableCars(String pickUpLocation,LocalDateTime startDateTime,LocalDateTime endDateTime) {

        validateBookingStartDateAndTime(startDateTime, endDateTime);
        List<Car> cars = bookingRepo.findAvailableCarsBetweenStartAndEnd(pickUpLocation,startDateTime, endDateTime);
        System.out.println(cars.toString());
        return cars;
    }
}