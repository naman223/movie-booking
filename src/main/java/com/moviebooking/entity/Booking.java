package com.moviebooking.entity;

import lombok.AllArgsConstructor;
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
    private int id;
    private int showId;

    @ElementCollection
    @CollectionTable(name="seats", joinColumns=@JoinColumn(name="id"))
    @Column(name="seats")
    private List<Integer> seats;
    private String userId;
    private BookingStatus bookingStatus;

    public Booking(@NonNull int showId, @NonNull String userId,
                   @NonNull List<Integer> seats) {
        this.showId = showId;
        this.seats = seats;
        this.userId = userId;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public void isConfirmed() throws Exception {
        if (BookingStatus.CREATED != this.bookingStatus) {
            throw new Exception("Invalid State");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void isExpired() throws Exception {
        if (BookingStatus.CREATED != this.bookingStatus) {
            throw new Exception("Invalid State");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }

}
