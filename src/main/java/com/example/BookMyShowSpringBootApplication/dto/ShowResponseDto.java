package com.example.BookMyShowSpringBootApplication.dto;


import com.example.BookMyShowSpringBootApplication.entity.Show;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShowResponseDto {
    private Long id;

    private String cinema;

    private String cinemaHall;

    private MovieResponseDto movie;

    private Date startDateTime;

    private Date endDateTime;

    private List<ShowSeatDto> showSeats = new ArrayList<>();

    public ShowResponseDto(Show show) {
        this.id = show.getId();
        this.cinemaHall = show.getCinemaHall().getName();
        this.cinema = show.getCinemaHall().getCinema().getName();
        this.movie = new MovieResponseDto(show.getMovie());
        this.startDateTime = show.getStartDateTime();
        this.endDateTime = show.getEndDateTime();
        this.showSeats = show
                .getShowSeats()
                .stream()
                .map(showSeat -> new ShowSeatDto(showSeat))
                .collect(Collectors.toList());
    }


}
