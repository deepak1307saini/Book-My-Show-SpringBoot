package com.example.BookMyShowSpringBootApplication.controller;


import com.example.BookMyShowSpringBootApplication.dto.CinemaHallSeatDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaHallSeatResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.service.CinemaHallSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cinemas/{cinemaId}/cinemaHall/{cinemaHallName}/cinemaHallSeats")
public class CinemaHallSeatController {

    @Autowired
    CinemaHallSeatService cinemaHallSeatService;

    @GetMapping
    public List<CinemaHallSeatResponseDto> getAllCinemaHallSeats(@PathVariable Long cinemaId, @PathVariable String cinemaHallName) {
        return cinemaHallSeatService.getAllCinemaHallSeats(cinemaId, cinemaHallName);
    }

    @GetMapping("/{seatNo}")
    public CinemaHallSeatResponseDto getCinemaHallSeat(@PathVariable Long cinemaId, @PathVariable String cinemaHallName, @PathVariable String seatNo) {
        return cinemaHallSeatService.getCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
    }

    @PostMapping
    public ResponseDto addCinemaHallSeat(@PathVariable Long cinemaId, @PathVariable String cinemaHallName,
                                         @Valid @RequestBody CinemaHallSeatDto audiSeatRequestDTO) {
        return cinemaHallSeatService.addCinemaHallSeat(cinemaId, cinemaHallName, audiSeatRequestDTO);
    }

    @PutMapping("/{seatNo}")
    public ResponseDto updateCinemaHallSeat(@PathVariable Long cinemaId, @PathVariable String cinemaHallName,
                                            @PathVariable String seatNo, @Valid @RequestBody CinemaHallSeatDto audiSeatRequestDTO) {
        return cinemaHallSeatService.updateCinemaHallSeat(cinemaId, cinemaHallName, seatNo, audiSeatRequestDTO);
    }

    @DeleteMapping("/{seatNo}")
    public ResponseDto deleteCinemaHallSeat(@PathVariable Long cinemaId, @PathVariable String cinemaHallName, @PathVariable String seatNo) {
        return cinemaHallSeatService.deleteCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
    }
}