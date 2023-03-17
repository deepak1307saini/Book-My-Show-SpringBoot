package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.BookMyShowSpringBootApplication.entity.Show;

import java.util.List;


@Repository
public interface ShowRepository extends JpaRepository<Show, Long>, JpaSpecificationExecutor<Show> {
    List<Show> findByCinemaHall(CinemaHall cinemaHall);

    List<Show> findByMovie(Movie movie);

    boolean existsByIdAndCinemaHall(Long showId, CinemaHall cinemaHall);

    boolean existsByIdAndMovie(Long showId, Movie movie);
}