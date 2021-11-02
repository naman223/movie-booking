package com.moviebooking.provider;

import com.moviebooking.entity.Seat;
import com.moviebooking.entity.Show;

import java.util.List;

public interface SeatBlocker {
    void lockSeats(Show show, List<Seat> seats, String user) throws Exception;
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
