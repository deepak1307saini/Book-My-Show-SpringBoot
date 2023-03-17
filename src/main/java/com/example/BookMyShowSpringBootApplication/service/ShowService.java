package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Show;

import java.util.List;

public interface ShowService {
    List<ShowResponseDto> getAllShows(Long cinemaId, String cinemaHallName);

    ResponseDto addShow(Long cinemaId, String cinemaHallName, ShowDto showDto);

    List<ShowResponseDto> getAllShows(Long movieId);

    ResponseDto updateShow(Long cinemaId, String cinemaHallName, Long showId, ShowDto showDto);

    ResponseDto deleteShow(Long cinemaId, String cinemaHallName, Long showId);

    ShowResponseDto getShow(Long cinemaId, String cinemaHallName, Long showId);
}
