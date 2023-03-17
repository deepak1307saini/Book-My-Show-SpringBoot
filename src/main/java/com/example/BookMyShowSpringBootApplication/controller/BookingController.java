package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.BookingDto;
import com.example.BookMyShowSpringBootApplication.dto.BookingResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user/{userId}")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/bookings")
    List<BookingResponseDto> getAllBookings(@PathVariable Long userId) {
        return bookingService.getAllBookings(userId);
    }

    @GetMapping("/bookings/{bookingId}")
    BookingResponseDto getBooking(@PathVariable Long userId, @PathVariable Long bookingId) {
        return bookingService.getBooking(userId, bookingId);
    }

    @PostMapping("/movies/{movieId}/shows/{showId}/book")
    ResponseDto bookShow(@PathVariable Long userId, @PathVariable Long movieId, @PathVariable Long showId,
                         @Valid @RequestBody BookingDto bookingDto) {
        return bookingService.bookShow(userId, movieId, showId, bookingDto);
    }

    @DeleteMapping("/bookings/{bookingId}")
    ResponseDto cancelBooking(@PathVariable Long userId, @PathVariable Long bookingId) {
        return bookingService.cancelBooking(userId, bookingId);
    }
}