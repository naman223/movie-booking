package com.moviebooking.services;

import com.moviebooking.entity.Seat;
import com.moviebooking.repository.SeatRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public Optional<Seat> getSeat(@NonNull int seatId) {
        return seatRepository.findById(seatId);
    }

    public Seat createSeat(@NonNull Seat seat) {
        return seatRepository.save(seat);
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public List<Seat> findAllSeats(@NonNull List<Integer> seatIds) {
        return seatRepository.findAllById(seatIds);
    }


}
