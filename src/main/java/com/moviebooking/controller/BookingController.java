package com.moviebooking.controller;

import com.moviebooking.entity.Booking;
import com.moviebooking.entity.BookingStatus;
import com.moviebooking.entity.Show;
import com.moviebooking.entity.Theater;
import com.moviebooking.request.BookingRequester;
import com.moviebooking.services.BookingService;
import com.moviebooking.services.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/bookings")
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/")
    public  ResponseEntity<Booking> createBooking(@RequestBody BookingRequester bookingRequester) {
        log.info("Inside createBooking method of BookingController");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try {
            Booking booking = bookingService.createBooking(bookingRequester);
            return new ResponseEntity<>(booking, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Booking failed: " + e.getMessage());
            Booking booking = new Booking();
            booking.setBookingStatus(BookingStatus.FAILED);
            booking.setUserId(bookingRequester.getUserId());
            return new ResponseEntity<>(booking, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Booking findBookingByID(@PathVariable("id") Integer bookingId) {
        log.info("Inside findBookingByID method of BookingController");
        return bookingService.getBooking(bookingId);
    }

    @GetMapping("/shows/{id}")
    public ResponseEntity<List<Booking>> findBookingByShowID(@PathVariable("id") Integer showId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        log.info("Inside findBookingByShowID method of BookingController");
        try {
            List<Booking> bookings = bookingService.getAllBookings(showId);;
            return new ResponseEntity<>(bookings, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Wrong input values in ShowRequester " + e.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

}
