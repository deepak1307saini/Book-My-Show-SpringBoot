package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.Booking;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookingResponseDto {

    List<String> seatNo = new ArrayList<>();
    private int numberOfSeats;
    private int totalPrice;
    private Date bookingTime;
    private String movieName;
    private CityDto city;
    private String cinema;
    private String cinemaHall;
    private Date startTime;
    private Date endTime;

    public BookingResponseDto(Booking booking) {
        this.numberOfSeats = booking.getNumberOfSeats();
        this.totalPrice = booking.getTotalPrice();
        this.bookingTime = booking.getBookingTime();
        this.movieName = booking.getShow().getMovie().getName();
        this.city = new CityDto(booking.getShow().getCinemaHall().getCinema().getCity());
        this.cinema = booking.getShow().getCinemaHall().getCinema().getName();
        this.cinemaHall = booking.getShow().getCinemaHall().getName();
        this.startTime = booking.getShow().getStartDateTime();
        this.endTime = booking.getShow().getEndDateTime();
        this.seatNo = booking
                .getShowSeats()
                .stream()
                .map(showSeat -> showSeat.getCinemaHallSeat().getSeatNo())
                .collect(Collectors.toList());
    }
}
