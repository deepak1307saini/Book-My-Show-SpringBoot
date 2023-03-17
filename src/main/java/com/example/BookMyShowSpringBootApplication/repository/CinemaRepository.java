package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema,Long> {

}
