package com.moviebooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;

    @ElementCollection
    @CollectionTable(name="screens", joinColumns=@JoinColumn(name="id"))
    @Column(name="screens")
    private List<Integer> screens;
}
