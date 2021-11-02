package com.moviebooking.services;

import com.moviebooking.entity.*;
import com.moviebooking.provider.InMemorySeatBlockerImpl;
import com.moviebooking.provider.SeatBlocker;
import com.moviebooking.repository.BookingRepository;
import com.moviebooking.request.BookingRequester;
import javassist.NotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private SeatBlocker seatBlocker;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatService seatService;

    @Autowired
    ShowService showService;

    public BookingService() {
        this.seatBlocker = new InMemorySeatBlockerImpl(120);
    }

    public Booking getBooking(@NonNull final int bookingId) {
        return bookingRepository.findById(bookingId).orElse(new Booking());
    }

    public List<Booking> getAllBookings(@NonNull final int showId) throws Exception {
        Optional<Show> show = Optional.ofNullable(showService.getShow(showId)
                .orElseThrow(() -> new NotFoundException("Show ID " + showId + " not found.")));
        return bookingRepository.findByShow(show.get());
    }

    public Booking createBooking(BookingRequester bookingRequester) throws Exception {
        Optional<Show> show = Optional.ofNullable(showService.getShow(bookingRequester.getShowId())
                .orElseThrow(() -> new NotFoundException("Show ID " + bookingRequester.getShowId() + " not found.")));
        List<Seat> seats = new ArrayList<>();
        for(Integer id : bookingRequester.getSeats()) {
            Optional<Seat> seat = Optional.ofNullable(seatService.getSeat(id)
                    .orElseThrow(() -> new NotFoundException("Seat " + id + " not found.")));
            seats.add(seat.get());
        }
        if (isAnySeatAlreadyBooked(show.get(), seats)) {
            throw new Exception("Seat is already Booked");
        }
        seatBlocker.lockSeats(show.get(),seats, bookingRequester.getUserId());
        Booking booking = new Booking(show.get(), bookingRequester.getUserId(),seats);
        // Call payment service if response is 200.
        // Check for payment timeout if - it reaches call expiredBooking
        confirmBooking(booking);
        bookingRepository.save(booking);
        return booking;
    }

    public void confirmBooking(@NonNull final Booking booking) throws Exception {
        for (Seat seat : booking.getSeats()) {
            if (!seatBlocker.validateLock(booking.getShow(), seat, booking.getUserId())) {
                throw new NotFoundException("Seat validation is failed for user");
            }
        }
        booking.confirmBooking();
    }


    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }

    public List<Seat> getBookedSeats(@NonNull final Show show) {
        List<Booking> bookings = bookingRepository.findBookingByShow(show);
        List<Seat> seats = new ArrayList<>();
        for(Booking booking : bookings) {
            seats.addAll(booking.getSeats());
        }
        return seats;
    }

}
