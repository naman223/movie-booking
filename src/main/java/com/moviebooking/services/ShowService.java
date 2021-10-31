package com.moviebooking.services;

import com.moviebooking.entity.Movie;
import com.moviebooking.entity.Screen;
import com.moviebooking.entity.Show;
import com.moviebooking.repository.ShowRepository;
import com.moviebooking.request.ShowRequester;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    ScreenService screenService;

    public Optional<Show> getShow(@NonNull int showId) {
        return showRepository.findById(showId);
    }

    public Show createShow(@NonNull ShowRequester showRequester) throws NotFoundException {
        Show show = new Show();
        Integer movieId = showRequester.getMovieId();
        Integer screenId = showRequester.getScreenId();
        Movie movie = movieService.getMovie(movieId)
                .orElseThrow(() ->new NotFoundException("Movie ID " + movieId + " not found."));
        Screen screen = screenService.getScreen(screenId)
                .orElseThrow(() ->new NotFoundException("Screen ID " + screenId + " not found."));
        show.setMovie(movie);
        show.setScreen(screen);
        show.setDurationInMinutes(showRequester.getDurationInMinutes());
        show.setShowTime(showRequester.getShowTime());
        log.info(String.valueOf(show));
        showRepository.save(show);
        return show;
    }
}
