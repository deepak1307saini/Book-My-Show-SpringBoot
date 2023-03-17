package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.CinemaHallDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaHallResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.helper.CinemaHallHelper;
import com.example.BookMyShowSpringBootApplication.repository.CinemaHallRepository;
import com.example.BookMyShowSpringBootApplication.service.CinemaHallService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data

public class CinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    CinemaHallRepository cinemaHallRepository;

    @Autowired
    CinemaHallHelper cinemaHallHelper;


    @Override
    public List<CinemaHallResponseDto> getAllCinemaHalls(Long cinemaId) {
        cinemaHallHelper.checkCinema(cinemaId);

        Cinema cinema = cinemaHallHelper.getCinema(cinemaId);
        return cinemaHallRepository.findByCinema(cinema).stream().map(cinemaHall -> new CinemaHallResponseDto(cinemaHall)).collect(Collectors.toList());
    }

    @Override
    public CinemaHallResponseDto getCinemaHall(Long cinemaId, String cinemaHallName) {
        cinemaHallHelper.checkCinemaHall(cinemaId, cinemaHallName);
        CinemaHall cinemaHall = cinemaHallHelper.getCinemaHall(cinemaId, cinemaHallName);
        return new CinemaHallResponseDto(cinemaHall);
    }

    @Override
    public ResponseDto addCinemaHall(Long cinemaId, CinemaHallDto cinemaHallDto) {
        cinemaHallHelper.canAdd(cinemaId, cinemaHallDto.getName());

        Cinema cinema = cinemaHallHelper.getCinema(cinemaId);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setName(cinemaHallDto.getName());
        cinemaHall.setCinema(cinema);
        cinemaHallRepository.save(cinemaHall);

        return new ResponseDto(true, String.format("cinemaHall %s added successfully", cinemaHall.getName()));
    }

    @Override
    public ResponseDto updateCinemaHall(Long cinemaId, String name, CinemaHallDto cinemaHallDto) {
        cinemaHallHelper.canUpdate(cinemaId, name, cinemaHallDto.getName());

        CinemaHall cinemaHall = cinemaHallHelper.getCinemaHall(cinemaId, name);
        cinemaHall.setName(cinemaHallDto.getName());
        cinemaHallRepository.save(cinemaHall);

        return new ResponseDto(true, String.format("cinemaHall %s updated successfully", name));
    }

    @Override
    public ResponseDto deleteCinemaHall(Long cinemaId, String cinemaHallName) {
        cinemaHallHelper.canDelete(cinemaId, cinemaHallName);

        CinemaHall cinemaHall = cinemaHallHelper.getCinemaHall(cinemaId, cinemaHallName);
        cinemaHallRepository.delete(cinemaHall);

        return new ResponseDto(true, String.format("cinemaHall %s deleted successfully", cinemaHallName));
    }

}