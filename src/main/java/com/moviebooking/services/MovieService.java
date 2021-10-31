package com.moviebooking.services;

import com.moviebooking.entity.Movie;
import com.moviebooking.repository.MovieRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Optional<Movie> getMovie(@NonNull int movieId) {
        return movieRepository.findById(movieId);
    }

    public Movie createMovie(@NonNull Movie movie) {
        return movieRepository.save(movie);
    }

}
