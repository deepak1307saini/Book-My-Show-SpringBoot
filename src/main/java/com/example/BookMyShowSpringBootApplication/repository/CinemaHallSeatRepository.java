package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHallSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CinemaHallSeatRepository extends JpaRepository<CinemaHallSeat, Long> {
    List<CinemaHallSeat> findByCinemaHall(CinemaHall cinemaHall);

    Optional<CinemaHallSeat> findByCinemaHallAndSeatNo(CinemaHall cinemaHall, String seatNo);

    boolean existsByCinemaHallAndSeatNo(CinemaHall cinemaHall, String seatNo);

    int countByCinemaHall(CinemaHall cinemaHall);
}
