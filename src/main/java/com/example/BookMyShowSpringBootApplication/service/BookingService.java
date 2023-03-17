package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.BookingDto;
import com.example.BookMyShowSpringBootApplication.dto.BookingResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Booking;

import java.util.List;

public interface BookingService {
    List<BookingResponseDto> getAllBookings(Long userId);

    BookingResponseDto getBooking(Long userId, Long bookingId);

    ResponseDto bookShow(Long userId, Long movieId, Long showId, BookingDto bookingDto);

    ResponseDto cancelBooking(Long userId, Long bookingId);
}
