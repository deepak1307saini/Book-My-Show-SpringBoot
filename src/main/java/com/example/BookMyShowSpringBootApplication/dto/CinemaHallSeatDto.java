/**
 *
 */
package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.enums.SeatType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class CinemaHallSeatDto {
    @NotBlank
    private String seatNo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}