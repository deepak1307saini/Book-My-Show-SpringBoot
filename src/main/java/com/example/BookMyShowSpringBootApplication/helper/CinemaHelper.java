package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.dto.CinemaDto;
import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import com.example.BookMyShowSpringBootApplication.entity.City;
import com.example.BookMyShowSpringBootApplication.repository.CinemaRepository;
import com.example.BookMyShowSpringBootApplication.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class CinemaHelper {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    public void mapCinemaToCinema(CinemaDto cinemaDto, Cinema cinema) {
        cinema.setName(cinemaDto.getName());
        cinema.setAddress(cinemaDto.getAddress());
        cinema.setCity(getCity(cinemaDto.getCity(), cinemaDto.getPinCode(), cinemaDto.getState()));
    }

    private City getCity(String city, String pin, String state) {
        Optional<City> cityOptional = cityRepository.findByNameAndPinCode(city, pin);
        return cityOptional.orElseGet(() -> cityRepository.save(new City(city, pin, state)));
    }

    public void checkCinema(Long id) {
        if (!cinemaRepository.existsById(id))
            throw new EntityNotFoundException("Invalid Cinema id");
    }

    public Cinema getCinema(Long cinemaId) {
        return cinemaRepository.findById(cinemaId).get();
    }

    public void canUpdate(Long theatreId) {
        checkCinema(theatreId);
    }

    public void canDelete(Long theatreId) {
        checkCinema(theatreId);
    }
}