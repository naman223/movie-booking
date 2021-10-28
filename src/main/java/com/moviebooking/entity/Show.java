package com.moviebooking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int movieId;
    private int screenId;
    private Date showTime;
    private int durationInMinutes;
}
