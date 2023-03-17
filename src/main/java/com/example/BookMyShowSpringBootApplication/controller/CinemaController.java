package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.CinemaDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.service.impl.CinemaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    CinemaServiceImpl cinemaService;


    @GetMapping
    public List<CinemaResponseDto> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{cinemaId}")
    public CinemaResponseDto getCinema(@PathVariable("cinemaId") Long cinemaId) {
        return cinemaService.getCinema(cinemaId);
    }

    @PostMapping
    public ResponseDto addCinema(@Valid @RequestBody CinemaDto cinemaDto) {
        return cinemaService.addCinema(cinemaDto);
    }

    @PutMapping("/{cinemaId}")
    public ResponseDto updateCinema(@PathVariable Long cinemaId, @Valid @RequestBody CinemaDto cinemaDto) {
        return cinemaService.updateCinema(cinemaId, cinemaDto);
    }

    @DeleteMapping("/{cinemaId}")
    public ResponseDto deleteCinema(@PathVariable Long cinemaId) {
        return cinemaService.deleteCinema(cinemaId);
    }
}