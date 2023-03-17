package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CinemaResponseDto {
    private Long id;
    private String name;

    private String address;

    private CityDto city;

    private List<String> cinemaHalls = new ArrayList<>();

    public CinemaResponseDto(Cinema cinema) {
        this.id = cinema.getId();
        this.name = cinema.getName();
        this.address = cinema.getAddress();
        this.city = new CityDto(cinema.getCity());
        cinemaHalls = cinema
                .getCinemaHalls()
                .stream()
                .map(cinemaHall -> {return cinemaHall.getName();})
                .collect(Collectors.toList());
    }

}

