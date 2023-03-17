package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.CinemaHallSeatDto;
import com.example.BookMyShowSpringBootApplication.dto.CinemaHallSeatResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHallSeat;
import com.example.BookMyShowSpringBootApplication.helper.CinemaHallSeatHelper;
import com.example.BookMyShowSpringBootApplication.repository.CinemaHallSeatRepository;
import com.example.BookMyShowSpringBootApplication.service.CinemaHallSeatService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CinemaHallSeatServiceImpl implements  CinemaHallSeatService {

    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;

    @Autowired
    CinemaHallSeatHelper cinemaHallSeatHelper;

    @Override
    public List<CinemaHallSeatResponseDto> getAllCinemaHallSeats(Long cinemaId, String cinemaHallName) {
        cinemaHallSeatHelper.checkCinemaHall(cinemaId, cinemaHallName);

        CinemaHall cinemaHall = cinemaHallSeatHelper.getCinemaHall(cinemaId, cinemaHallName);
        return cinemaHallSeatRepository.findByCinemaHall(cinemaHall).stream().map(cinemaHallSeat -> new CinemaHallSeatResponseDto(cinemaHallSeat)).collect(Collectors.toList());
    }

    @Override
    public CinemaHallSeatResponseDto getCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo) {
        cinemaHallSeatHelper.checkCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
        CinemaHallSeat cinemaHallSeat = cinemaHallSeatHelper.getCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
        return new CinemaHallSeatResponseDto(cinemaHallSeat);
    }

    @Override
    public ResponseDto addCinemaHallSeat(Long cinemaId, String cinemaHallName, CinemaHallSeatDto cinemaHallSeatDto) {
        cinemaHallSeatHelper.canAdd(cinemaId, cinemaHallName, cinemaHallSeatDto.getSeatNo(), cinemaHallSeatDto.getSeatType());

        CinemaHall cinemaHall = cinemaHallSeatHelper.getCinemaHall(cinemaId, cinemaHallName);
        System.out.println(cinemaHall.getName());

        CinemaHallSeat cinemaHallSeat = new CinemaHallSeat();
        cinemaHallSeat.setSeatNo(cinemaHallSeatDto.getSeatNo());
        cinemaHallSeat.setSeatType(cinemaHallSeatDto.getSeatType());
        cinemaHallSeat.setCinemaHall(cinemaHall);
        cinemaHallSeatRepository.save(cinemaHallSeat);
        return new ResponseDto(true, String.format("cinemaHall seat %s saved successfully", cinemaHallSeatDto.getSeatNo()));
    }

    @Override
    public ResponseDto updateCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo, CinemaHallSeatDto cinemaHallSeatDto) {
        cinemaHallSeatHelper.canUpdate(cinemaId, cinemaHallName, seatNo, cinemaHallSeatDto.getSeatNo(), cinemaHallSeatDto.getSeatType());

        CinemaHallSeat cinemaHallSeat = cinemaHallSeatHelper.getCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
        cinemaHallSeat.setSeatNo(cinemaHallSeatDto.getSeatNo());
        cinemaHallSeat.setSeatType(cinemaHallSeatDto.getSeatType());
        cinemaHallSeatRepository.save(cinemaHallSeat);
        return new ResponseDto(true, String.format("cinemaHall seat %s updated successfully", seatNo));
    }

    @Override
    public ResponseDto deleteCinemaHallSeat(Long cinemaId, String cinemaHallName, String seatNo) {
        cinemaHallSeatHelper.canDelete(cinemaId, cinemaHallName, seatNo);
        CinemaHallSeat cinemaHallSeat = cinemaHallSeatHelper.getCinemaHallSeat(cinemaId, cinemaHallName, seatNo);
        cinemaHallSeatRepository.delete(cinemaHallSeat);
        return new ResponseDto(true, String.format("cinemaHall seat %s deleted successfully", seatNo));
    }
}