package com.moviebooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }
}
