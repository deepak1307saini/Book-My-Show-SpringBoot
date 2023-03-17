package com.example.BookMyShowSpringBootApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numberOfSeats;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    @JsonIgnore
    private Show show;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<ShowSeat> showSeats = new ArrayList<>();

    public Booking(Integer noOfSeats, Integer totalPrice, Date bookingTime, Show show, User user) {
        this.numberOfSeats = noOfSeats;
        this.totalPrice = totalPrice;
        this.bookingTime = bookingTime;
        this.show = show;
        this.user = user;
    }


}