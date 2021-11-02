package com.moviebooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="bookng")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "show_id", referencedColumnName = "id")
    private Show show;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "booking_id")
    private List<Seat> seats;
    private String userId;
    private BookingStatus bookingStatus;

    public Booking(@NonNull Show show, @NonNull String userId,
                   @NonNull List<Seat> seats) {
        this.show = show;
        this.seats = seats;
        this.userId = userId;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public void confirmBooking() throws Exception {
        if (BookingStatus.CREATED != this.bookingStatus) {
            throw new Exception("Invalid State");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expiredBooking() throws Exception {
        if (BookingStatus.CREATED != this.bookingStatus) {
            throw new Exception("Invalid State");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }

}
