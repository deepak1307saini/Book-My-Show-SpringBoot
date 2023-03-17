package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.entity.Show;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;
import com.example.BookMyShowSpringBootApplication.repository.BookingRepository;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookingHelper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ShowHelper showHelper;

    public User getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public void checkShow(Long movieId, Long showId) {
        showHelper.checkShow(movieId, showId);
    }

    public Show getShow(Long showId) {
        return showHelper.getShow(showId);
    }

    public void checkBooking(Long userId, Long bookingId) {
        User user = getUser(userId);
        if (!bookingRepository.findByIdAndUser(bookingId, user).isPresent())
            throw new EntityNotFoundException("invalid booking id");
    }

    public void checkSeats(Show show, List<String> seatNos) {
        Set<String> allShowSeatNos = show.getShowSeats()
                .stream()
                .map(showSeat -> showSeat.getCinemaHallSeat().getSeatNo())
                .collect(Collectors.toSet());

        List<String> invalidSeats = seatNos.stream()
                .filter(seatNo -> !allShowSeatNos.contains(seatNo))
                .collect(Collectors.toList());

        if (!invalidSeats.isEmpty())
            throw new IllegalArgumentException(String.format("invalid seats: %s", invalidSeats));
    }

    public void canBook(Long movieId, Long showId, List<String> seatNos) {
        checkShow(movieId, showId);
        Show show = getShow(showId);
        checkSeats(show, seatNos);

        Set<String> seatNoSet = new HashSet<>(seatNos);
        List<String> bookedSeats = show.getShowSeats()
                .stream()
                .filter(showSeat -> seatNoSet.contains(showSeat.getCinemaHallSeat().getSeatNo()))
                .filter(showSeat -> showSeat.getSeatStatus() == SeatStatus.Booked)
                .map(showSeat -> showSeat.getCinemaHallSeat().getSeatNo())
                .collect(Collectors.toList());

        if (!bookedSeats.isEmpty())
            throw new IllegalArgumentException(String.format("seats %s are already booked", bookedSeats));
    }

    public int calcTotalPrice(Show show, int noOfSeats) {
        int price = show.getShowSeats()
                .get(0).getPrice();
        return price * noOfSeats;
    }

    public void canCancel(Long userId, Long bookingId) {
        checkBooking(userId, bookingId);
    }
}