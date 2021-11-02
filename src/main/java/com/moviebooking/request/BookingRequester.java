package com.moviebooking.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moviebooking.entity.BookingStatus;
import com.moviebooking.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequester {
    private int showId;
    private List<Integer> seats;
    private String userId;
}
