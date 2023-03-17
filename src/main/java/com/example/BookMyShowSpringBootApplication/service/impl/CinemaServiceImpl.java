package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.CinemaDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import com.example.BookMyShowSpringBootApplication.helper.CinemaHelper;
import com.example.BookMyShowSpringBootApplication.repository.CinemaRepository;
import com.example.BookMyShowSpringBootApplication.service.CinemaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    CinemaHelper cinemaHelper;

    @Override
    public List<CinemaResponseDto> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        return cinemas.stream().map(cinema -> new CinemaResponseDto(cinema)).collect(Collectors.toList());
    }

    @Override
    public CinemaResponseDto getCinema(Long cinemaId) {
        cinemaHelper.checkCinema(cinemaId);
        Cinema cinema = cinemaHelper.getCinema(cinemaId);
        return new CinemaResponseDto(cinema);
    }

    @Override
    public ResponseDto addCinema(CinemaDto cinemaDto) {
        Cinema cinema = new Cinema();
        cinemaHelper.mapCinemaToCinema(cinemaDto, cinema);
        cinemaRepository.save(cinema);
        return new ResponseDto(true, String.format("Cinema %s saved successfully", cinema.getName()));
    }

    @Override
    public ResponseDto updateCinema(Long cinemaId, CinemaDto cinemaDto) {
        cinemaHelper.canUpdate(cinemaId);

        Cinema cinema = cinemaHelper.getCinema(cinemaId);
        cinemaHelper.mapCinemaToCinema(cinemaDto, cinema);
        cinemaRepository.save(cinema);

        return new ResponseDto(true, String.format("Cinema %s updated successfully", cinema.getName()));
    }

    @Override
    public ResponseDto deleteCinema(Long cinemaId) {
        cinemaHelper.canDelete(cinemaId);

        Cinema cinema = cinemaHelper.getCinema(cinemaId);
        cinemaRepository.delete(cinema);

        return new ResponseDto(true, String.format("Cinema %s deleted successfully", cinema.getName()));
    }
}