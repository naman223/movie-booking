package com.moviebooking.provider;

import com.moviebooking.entity.Seat;
import com.moviebooking.entity.SeatLock;
import com.moviebooking.entity.Show;
import lombok.NonNull;

import java.util.*;

public class InMemorySeatBlockerImpl implements  SeatBlocker {

    private final Map<Show, Map<Seat, SeatLock>> locks;
    private final Integer lockTimeout;

    public InMemorySeatBlockerImpl(@NonNull final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) throws Exception {
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat)) {
                throw new Exception("Seat is already Blocked.");
            }
        }
        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }

    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        final List<Seat> lockedSeats = new ArrayList<>();

        if (!locks.containsKey(show)) {
            return lockedSeats;
        }

        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }
}
