package com.moviebooking.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="screen")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ElementCollection
    @CollectionTable(name="seats", joinColumns=@JoinColumn(name="id"))
    @Column(name="seats")
    private List<Integer> seats;

    public Screen(@NonNull int id, @NonNull String name) {
        this.id = id;
        this.name = name;
        this.seats = new ArrayList<>();
    }

    public void addSeat(@NonNull int seatId) {
        this.seats.add(seatId);
    }

}
