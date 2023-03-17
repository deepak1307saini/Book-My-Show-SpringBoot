package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHallSeat;
import com.example.BookMyShowSpringBootApplication.enums.SeatType;
import com.example.BookMyShowSpringBootApplication.repository.CinemaHallSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class CinemaHallSeatHelper {
    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;

    @Autowired
    CinemaHallHelper cinemaHallHelper;

    public void checkCinemaHall(Long cinemaId, String cinemaHallName) {
        cinemaHallHelper.checkCinemaHall(cinemaId, cinemaHallName);
    }

    public CinemaHall getCinemaHall(Long cinemaId, String cinemaHallName) {
        return cinemaHallHelper.getCinemaHall(cinemaId, cinemaHallName);
    }

    public void checkCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo) {
        checkCinemaHall(cinemaId, cinemaHallName);

        CinemaHall cinemaHall = getCinemaHall(cinemaId, cinemaHallName);
        if (!cinemaHallSeatRepository.existsByCinemaHallAndSeatNo(cinemaHall, seatNo))
            throw new EntityNotFoundException("invalid seat no");
    }

    public CinemaHallSeat getCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo) {
        CinemaHall cinemaHall = getCinemaHall(cinemaId, cinemaHallName);
        return cinemaHallSeatRepository.findByCinemaHallAndSeatNo(cinemaHall, seatNo).get();
    }


    public void canAdd(Long cinemaId, String cinemaHallName, String newSeatNo, SeatType seatType) {
        checkCinemaHall(cinemaId, cinemaHallName);

        CinemaHall cinemaHall = getCinemaHall(cinemaId, cinemaHallName);
        if (cinemaHallSeatRepository.existsByCinemaHallAndSeatNo(cinemaHall, newSeatNo))
            throw new IllegalArgumentException(String.format("cinemaHall with seat no. %s already present", newSeatNo));
    }

    public void canUpdate(Long cinemaId, String cinemaHallName, String seatNo, String newSeatNo, SeatType seatType) {
        checkCinemaHallSeat(cinemaId, cinemaHallName, seatNo);

        CinemaHall cinemaHall = getCinemaHall(cinemaId, cinemaHallName);
        CinemaHallSeat cinemaHallSeat = getCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
        Optional<CinemaHallSeat> cinemaHallSeatOptional = cinemaHallSeatRepository.findByCinemaHallAndSeatNo(cinemaHall, newSeatNo);
        if (cinemaHallSeatOptional.isPresent() && !Objects.equals(cinemaHallSeatOptional.get().getId(), cinemaHallSeat.getId()))
            throw new IllegalArgumentException(String.format("cinemaHall with seat no. %s already present", newSeatNo));
    }

    public void canDelete(Long cinemaId, String cinemaHallName, String seatNo) {
        checkCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
    }
}