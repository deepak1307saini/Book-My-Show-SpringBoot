package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowResponseDto;
import com.example.BookMyShowSpringBootApplication.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cinemas/{cinemaId}/cinemaHall/{cinemaHallName}/shows")

public class ShowController {

    @Autowired
    ShowService showService;

    @GetMapping
    public List<ShowResponseDto> getAllShows(@PathVariable Long cinemaId, @PathVariable String cinemaHallName) {
        return showService.getAllShows(cinemaId, cinemaHallName);
    }

    @GetMapping("/{showId}")
    public ShowResponseDto getShow(@PathVariable Long cinemaId, @PathVariable String cinemaHallName, @PathVariable Long showId) {
        return showService.getShow(cinemaId, cinemaHallName, showId);
    }

    @PostMapping
    public ResponseDto addShow(@PathVariable Long cinemaId, @PathVariable String cinemaHallName,
                               @Valid @RequestBody ShowDto showRequestDTO) {
        return showService.addShow(cinemaId, cinemaHallName, showRequestDTO);
    }


    @PutMapping("/{showId}")
    public ResponseDto updateShow(@PathVariable Long cinemaId, @PathVariable String cinemaHallName,
                                  @PathVariable Long showId, @Valid @RequestBody ShowDto showRequestDTO) {
        return showService.updateShow(cinemaId, cinemaHallName, showId, showRequestDTO);
    }

    @DeleteMapping("/{showId}")
    public ResponseDto deleteShow(@PathVariable Long cinemaId, @PathVariable String cinemaHallName, @PathVariable Long showId) {
        return showService.deleteShow(cinemaId, cinemaHallName, showId);
    }
}