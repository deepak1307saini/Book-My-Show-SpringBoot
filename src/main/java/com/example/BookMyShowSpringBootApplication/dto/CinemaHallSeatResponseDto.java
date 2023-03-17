package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.CinemaHallSeat;
import com.example.BookMyShowSpringBootApplication.enums.SeatType;
import lombok.Data;

@Data
public class CinemaHallSeatResponseDto {
    private String seatNo;
    private SeatType seatType;

    public CinemaHallSeatResponseDto(CinemaHallSeat cinemaHallSeat) {
        this.seatNo = cinemaHallSeat.getSeatNo();
        this.seatType = cinemaHallSeat.getSeatType();
    }

}
