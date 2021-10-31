package com.moviebooking.controller;

import com.moviebooking.entity.Seat;
import com.moviebooking.services.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/seats")
@Slf4j
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/")
    public Seat saveSeat(@RequestBody Seat seat) {
        log.info("Inside savSeat method of SeatController");
        return seatService.createSeat(seat);
    }

    @GetMapping("/all")
    public List<Seat> getAllSeats() {
        log.info("Inside getAllSeats method of SeatController");
        return seatService.getAllSeats();
    }
}
