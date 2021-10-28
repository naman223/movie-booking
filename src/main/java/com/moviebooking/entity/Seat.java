package com.moviebooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int rowNo;
    private int seatNo;
}
