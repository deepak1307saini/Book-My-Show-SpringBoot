package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CinemaHallResponseDto {
    private String name;

    private String cinemaName;

    private List<CinemaHallSeatResponseDto> cinemaHallSeats = new ArrayList<>();

    public CinemaHallResponseDto(CinemaHall cinemaHall) {
        this.name = cinemaHall.getName();
        this.cinemaName = cinemaHall.getCinema().getName();
        this.cinemaHallSeats = cinemaHall
                .getCinemaHallSeats()
                .stream()
                .map(cinemaHallSeat -> new CinemaHallSeatResponseDto(cinemaHallSeat))
                .collect(Collectors.toList());
    }
}
