package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.CinemaHallDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaHallResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;

import java.util.List;

public interface CinemaHallService {
    List<CinemaHallResponseDto> getAllCinemaHalls(Long cinemaId);

    CinemaHallResponseDto getCinemaHall(Long cinemaId, String cinemaHallName);

    ResponseDto addCinemaHall(Long cinemaId, CinemaHallDto cinemaHallDto);

    ResponseDto updateCinemaHall(Long cinemaId, String name, CinemaHallDto cinemaHallDto);

    ResponseDto deleteCinemaHall(Long cinemaId, String cinemaHallName);
}
