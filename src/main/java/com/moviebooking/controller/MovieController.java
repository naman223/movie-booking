package com.moviebooking.controller;

import com.moviebooking.entity.Movie;
import com.moviebooking.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/")
    public Movie saveMovie(@RequestBody Movie movie) {
        log.info("Inside savMovie method of MovieController");
        return  movieService.createMovie(movie);
    }

    @GetMapping("/{id}")
    public Movie findMovieById(@PathVariable("id") Integer movieId) {
        log.info("Inside findMovieById method of MovieController");
        return movieService.getMovie(movieId).orElse(new Movie());
    }
}
