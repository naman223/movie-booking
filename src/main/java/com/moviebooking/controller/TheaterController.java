package com.moviebooking.controller;

import com.moviebooking.entity.Theater;
import com.moviebooking.services.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/theaters")
@Slf4j
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/")
    public Theater saveTheater(@RequestBody Theater theater) {
        log.info("Inside savSeat method of SeatController");
        return theaterService.createTheater(theater);
    }

    @GetMapping("/{id}")
    public Theater findTheaterByID(@PathVariable("id") Integer theaterId) {
        log.info("Inside findTheaterByID method of TheaterController");
        return theaterService.getTheater(theaterId).orElse(new Theater());
    }

    @PutMapping("/{id}")
    public Theater addScreensToTheater(@PathVariable("id") Integer theaterId, @RequestParam("screens") List<Integer> screens) {
        log.info("Inside addScreensToTheater method of TheaterController");
        return theaterService.addScreens(theaterId,screens);
    }

}
