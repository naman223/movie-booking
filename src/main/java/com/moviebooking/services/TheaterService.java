package com.moviebooking.services;

import com.moviebooking.entity.Screen;
import com.moviebooking.entity.Theater;
import com.moviebooking.repository.TheaterRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ScreenService screenService;

    public Optional<Theater> getTheater(@NonNull int theaterId) {
        return theaterRepository.findById(theaterId);
    }

    public Theater createTheater(@NonNull Theater theater) {
        theater.setScreens(new ArrayList<>());
        return theaterRepository.save(theater);
    }

    public Theater addScreens(@NonNull int theaterId, @NonNull List<Integer> screenIds) {
        List<Screen> screens = screenService.getAllScreens(screenIds);
        Theater theater = getTheater(theaterId).get();
        for(Screen screen : screens) {
            if(!theater.getScreens().contains(screen.getId()))
                theater.getScreens().add(screen);
        }
        return theaterRepository.save(theater);
    }


}
