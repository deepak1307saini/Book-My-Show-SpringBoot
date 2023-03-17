package com.example.BookMyShowSpringBootApplication.controller;


import com.example.BookMyShowSpringBootApplication.dto.CinemaHallDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaHallResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cinemas/{cinemaId}/cinemaHalls")
public class CinemaHallController {
    @Autowired
    CinemaHallService cinemaHallService;

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls(@PathVariable Long cinemaId) {
        return cinemaHallService.getAllCinemaHalls(cinemaId);
    }

    @GetMapping("/{name}")
    public CinemaHallResponseDto getCinemaHall(@PathVariable Long cinemaId, @PathVariable String name) {
        return cinemaHallService.getCinemaHall(cinemaId, name);
    }

    @PostMapping
    public ResponseDto addCinemaHall(@PathVariable Long cinemaId,
                                     @Valid @RequestBody CinemaHallDto cinemaHallDto) {
        return cinemaHallService.addCinemaHall(cinemaId, cinemaHallDto);
    }

    @PutMapping("/{name}")
    public ResponseDto updateCinemaHall(@PathVariable Long cinemaId, @PathVariable String name,
                                        @Valid @RequestBody CinemaHallDto cinemaHallDto) {
        return cinemaHallService.updateCinemaHall(cinemaId, name, cinemaHallDto);
    }

    @DeleteMapping("/{name}")
    public ResponseDto deleteCinemaHall(@PathVariable Long cinemaId, @PathVariable String name) {
        return cinemaHallService.deleteCinemaHall(cinemaId, name);
    }
}