package com.moviebooking.controller;

import com.moviebooking.entity.Screen;
import com.moviebooking.services.ScreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/screens")
@Slf4j
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping("/")
    public Screen saveScreen(@RequestBody Screen screen) {
        log.info("Inside saveScreen method of ScreenController");
        return screenService.createScreen(screen);
    }

    @GetMapping("/{id}")
    public Screen findScreenByID(@PathVariable("id") Integer screenId) {
        log.info("Inside findScreenByID method of ScreenController");
        return screenService.getScreen(screenId).orElse(new Screen());
    }

    @PutMapping("/{id}")
    public Screen addSeatsToScreen(@PathVariable("id") Integer screenId, @RequestParam("seats") List<Integer> seats) {
        log.info("Inside addSeatsToScreen method of ScreenController");
        return screenService.addSeats(screenId,seats);
    }
}
