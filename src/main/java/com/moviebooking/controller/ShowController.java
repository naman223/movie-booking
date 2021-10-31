package com.moviebooking.controller;

import com.moviebooking.entity.Show;
import com.moviebooking.request.ShowRequester;
import com.moviebooking.services.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shows")
@Slf4j
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/")
    public ResponseEntity<Show> createShow(@RequestBody ShowRequester showRequester) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        log.info("Inside saveShow method of ShowController");
        try {
            Show show = showService.createShow(showRequester);
            return new ResponseEntity<>(show, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Wrong input values in ShowRequester " + e.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Show findShow(@PathVariable("id") Integer showId) {
        log.info("Inside findTheaterByID method of TheaterController");
        return showService.getShow(showId).orElse(new Show());
    }
}
