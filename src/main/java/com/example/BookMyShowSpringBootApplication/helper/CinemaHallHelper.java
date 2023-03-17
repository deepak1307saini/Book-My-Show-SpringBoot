package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHallSeat;
import com.example.BookMyShowSpringBootApplication.repository.CinemaHallRepository;
import com.example.BookMyShowSpringBootApplication.repository.CinemaHallSeatRepository;
import com.example.BookMyShowSpringBootApplication.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Component
public class CinemaHallHelper {
    @Autowired
    CinemaHallRepository cinemaHallRepository;

    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;

    @Autowired
    CinemaHelper cinemaHelper;

    public void checkCinema(Long cinemaId) {
        cinemaHelper.checkCinema(cinemaId);
    }

    public Cinema getCinema(Long cinemaId) {
        return cinemaHelper.getCinema(cinemaId);
    }

    public void checkCinemaHall(Long cinemaId, String cinemaHallName) {
        checkCinema(cinemaId);
        Cinema cinema = getCinema(cinemaId);
        if (!cinemaHallRepository.existsByNameAndCinema(cinemaHallName, cinema))
            throw new EntityNotFoundException("Invalid CinemaHall no");
    }

    public CinemaHall getCinemaHall(Long cinemaId, String cinemaHallName) {
        Cinema cinema = getCinema(cinemaId);
        return cinemaHallRepository.findByNameAndCinema(cinemaHallName, cinema).get();
    }

    public int getSeatNo(CinemaHall cinemaHall) {
        return cinemaHallSeatRepository.countByCinemaHall(cinemaHall);
    }

    public void canAdd(Long cinemaId, String newCinemaHallName) {
        checkCinema(cinemaId);
        Cinema cinema = getCinema(cinemaId);

        if (cinemaHallRepository.existsByNameAndCinema(newCinemaHallName, cinema))
            throw new IllegalArgumentException(String.format("CinemaHall with no. %s already exists.", newCinemaHallName));
    }

    public void canUpdate(Long cinemaId, String name, String newCinemaHallName) {
        checkCinemaHall(cinemaId, name);
        Cinema cinema = getCinema(cinemaId);
        CinemaHall cinemaHall = getCinemaHall(cinemaId, name);

        Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository.findByNameAndCinema(newCinemaHallName, cinema);
        if (cinemaHallOptional.isPresent() && !Objects.equals(cinemaHallOptional.get().getId(), cinemaHall.getId()))
            throw new IllegalArgumentException(String.format("CinemaHall with no. %s already exists.", newCinemaHallName));
    }

    public void canDelete(Long cinemaId, String name) {
        checkCinemaHall(cinemaId, name);
    }
}