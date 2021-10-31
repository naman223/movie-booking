package com.moviebooking.services;

import com.moviebooking.entity.Screen;
import com.moviebooking.entity.Seat;
import com.moviebooking.repository.ScreenRepository;
import com.moviebooking.repository.SeatRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    SeatService seatService;

    public Optional<Screen> getScreen(@NonNull int screenId) {
        return screenRepository.findById(screenId);
    }

    public Screen createScreen(@NonNull Screen screen) {
        screen.setSeats(new ArrayList<>());
        return screenRepository.save(screen);
    }

    public Screen addSeats(@NonNull int screenId, @NonNull List<Integer> seatIds) {
        List<Seat> seats = seatService.findAllSeats(seatIds);
        Screen screen = getScreen(screenId).get();
        for(Seat seat : seats) {
            if(!screen.getSeats().contains(seat.getId()))
                screen.getSeats().add(seat);
        }
        return screenRepository.save(screen);
    }

    public List<Screen> getAllScreens(@NonNull List<Integer> screenIds) {
        return screenRepository.findAllById(screenIds);
    }

}
