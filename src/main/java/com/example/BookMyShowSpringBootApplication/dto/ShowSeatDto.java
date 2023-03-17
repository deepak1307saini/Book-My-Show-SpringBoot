package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.ShowSeat;
import com.example.BookMyShowSpringBootApplication.enums.SeatStatus;
import com.example.BookMyShowSpringBootApplication.enums.SeatType;
import lombok.Data;

@Data
public class ShowSeatDto {
    private String seatNo;
    private SeatType seatType;
    private int price;
    private SeatStatus seatStatus;

    public ShowSeatDto(ShowSeat showSeat) {
        this.seatNo = showSeat.getCinemaHallSeat().getSeatNo();
        this.seatType = showSeat.getCinemaHallSeat().getSeatType();
        this.seatStatus = showSeat.getSeatStatus();
        this.price = showSeat.getPrice();
    }

}
