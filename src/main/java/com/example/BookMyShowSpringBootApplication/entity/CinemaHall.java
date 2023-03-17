/**
 *
 */
package com.example.BookMyShowSpringBootApplication.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Table
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "cinema_id", nullable = false)
    @JsonIgnore
    private Cinema cinema;

    @OneToMany(mappedBy = "cinemaHall", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Show> shows = new ArrayList<>();

    @OneToMany(mappedBy = "cinemaHall", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<CinemaHallSeat> cinemaHallSeats = new ArrayList<>();

}