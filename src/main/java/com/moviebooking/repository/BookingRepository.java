package com.moviebooking.repository;

import com.moviebooking.entity.Booking;
import com.moviebooking.entity.Seat;
import com.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findBookingByShow(Show show);

    List<Booking> findByShow(Show show);
}
