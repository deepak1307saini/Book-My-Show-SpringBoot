/**
 *
 */
package com.example.BookMyShowSpringBootApplication.entity;

import javax.persistence.*;

import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Table(name = "show_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus = SeatStatus.Available;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    @JsonIgnore
    private Show show;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "CinemaHallSeat_id", nullable = false)
    @JsonIgnore
    private CinemaHallSeat cinemaHallSeat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "booked_seats",
            joinColumns = @JoinColumn(name = "show_seat_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    @JsonIgnore
    private Booking booking;

    public ShowSeat(Integer price, Show show, CinemaHallSeat cinemaHallSeat) {
        this.price = price;
        this.show = show;
        this.cinemaHallSeat = cinemaHallSeat;
    }

}