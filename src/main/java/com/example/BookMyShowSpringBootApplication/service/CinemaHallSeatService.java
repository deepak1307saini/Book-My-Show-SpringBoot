package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.CinemaHallSeatDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaHallSeatResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHallSeat;

import java.util.List;

public interface CinemaHallSeatService {
    List<CinemaHallSeatResponseDto> getAllCinemaHallSeats(Long cinemaId, String cinemaHallName);

    CinemaHallSeatResponseDto getCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo);

    ResponseDto addCinemaHallSeat(Long cinemaId, String cinemaHallName, CinemaHallSeatDto cinemaHallSeatDto);

    ResponseDto updateCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo, CinemaHallSeatDto cinemaHallSeatDto);

    ResponseDto deleteCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo);
}
