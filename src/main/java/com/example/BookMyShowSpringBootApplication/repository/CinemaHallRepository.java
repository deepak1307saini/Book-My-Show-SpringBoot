package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
    boolean existsByNameAndCinema(String name, Cinema cinema);

    List<CinemaHall> findByCinema(Cinema cinema);

    Optional<CinemaHall> findByNameAndCinema(String name, Cinema cinema);
}
