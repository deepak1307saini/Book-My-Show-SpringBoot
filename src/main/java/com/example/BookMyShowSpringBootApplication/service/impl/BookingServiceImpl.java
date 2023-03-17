package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.BookingDto;
import com.example.BookMyShowSpringBootApplication.dto.BookingResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Booking;
import com.example.BookMyShowSpringBootApplication.entity.Show;
import com.example.BookMyShowSpringBootApplication.entity.ShowSeat;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;
import com.example.BookMyShowSpringBootApplication.helper.BookingHelper;
import com.example.BookMyShowSpringBootApplication.repository.BookingRepository;
import com.example.BookMyShowSpringBootApplication.repository.ShowSeatRepository;
import com.example.BookMyShowSpringBootApplication.service.BookingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    BookingHelper bookingHelper;

    @Override
    public List<BookingResponseDto> getAllBookings(Long userId) {
        User user = bookingHelper.getUser(userId);
        return bookingRepository.findByUser(user).stream().map(booking -> new BookingResponseDto(booking)).collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto getBooking(Long userId, Long bookingId) {
        bookingHelper.checkBooking(userId, bookingId);
        Booking booking = bookingRepository.findById(bookingId).get();
        return new BookingResponseDto(booking);
    }

    @Override
    public ResponseDto bookShow(Long userId, Long movieId, Long showId, BookingDto bookingDto) {
        bookingHelper.canBook(movieId, showId, bookingDto.getSeatNumbers());

        User user = bookingHelper.getUser(userId);
        Show show = bookingHelper.getShow(showId);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(new Date());
        booking.setNumberOfSeats(bookingDto.getSeatNumbers().size());

        booking.setTotalPrice(bookingHelper.calcTotalPrice(show, booking.getNumberOfSeats()));
        booking.setShowSeats(bookingDto.getSeatNumbers()
                .stream()
                .map(seatNo -> {
                    ShowSeat showSeat = showSeatRepository.findByShowAndCinemaHallSeatSeatNo(show,
                            seatNo).get();
                    showSeat.setSeatStatus(SeatStatus.Booked);
                    showSeat.setBooking(booking);
                    showSeatRepository.save(showSeat);
                    return showSeat;
                }).collect(Collectors.toList()));
        bookingRepository.save(booking);
        return new ResponseDto(true, String.format("show with id %d booked successfully", showId));
    }

    @Override
    public ResponseDto cancelBooking(Long userId, Long bookingId) {
        bookingHelper.canCancel(userId, bookingId);

        Booking booking = bookingRepository.findById(bookingId).get();
        booking.getShowSeats()
                .forEach(showSeat -> {
                    showSeat.setSeatStatus(SeatStatus.Available);
                    showSeat.setBooking(null);
                    showSeatRepository.save(showSeat);
                });
        bookingRepository.deleteById(bookingId);
        return new ResponseDto(true, String.format("booking with id %d cancelled successfully", bookingId));
    }


}