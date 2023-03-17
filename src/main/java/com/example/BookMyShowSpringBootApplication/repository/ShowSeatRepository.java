package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.BookMyShowSpringBootApplication.entity.ShowSeat;

import java.util.List;
import java.util.Optional;


@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>, JpaSpecificationExecutor<ShowSeat> {
    List<ShowSeat> findByShow(Show show);

    Optional<ShowSeat> findByShowAndCinemaHallSeatSeatNo(Show show, String seatNo);
}