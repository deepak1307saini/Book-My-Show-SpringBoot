package com.example.BookMyShowSpringBootApplication.repository;


import com.example.BookMyShowSpringBootApplication.entity.Booking;
import com.example.BookMyShowSpringBootApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

    Optional<Booking> findByIdAndUser(Long bookingId, User user);
}