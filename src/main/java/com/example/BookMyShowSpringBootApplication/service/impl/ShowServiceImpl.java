package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.CinemaHall;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.entity.Show;
import com.example.BookMyShowSpringBootApplication.helper.ShowHelper;
import com.example.BookMyShowSpringBootApplication.repository.ShowRepository;
import com.example.BookMyShowSpringBootApplication.repository.ShowSeatRepository;
import com.example.BookMyShowSpringBootApplication.service.ShowService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ShowServiceImpl implements  ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowHelper showHelper;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Override
    public List<ShowResponseDto> getAllShows(Long cinemaId, String cinemaHallName) {
        showHelper.checkCinemaHall(cinemaId, cinemaHallName);
        CinemaHall cinemaHall = showHelper.getCinemaHall(cinemaId, cinemaHallName);
        return showHelper.showsToShowDto(showRepository.findByCinemaHall(cinemaHall));
    }

    @Override
    public ResponseDto addShow(Long cinemaId, String cinemaHallName, ShowDto showDto) {
        showHelper.canAdd(cinemaId, cinemaHallName, showDto);

        Show show = new Show();
        return showHelper.mapShowRequestToShow(show, showDto, cinemaId, cinemaHallName);
    }

    @Override
    public List<ShowResponseDto> getAllShows(Long movieId) {
        showHelper.checkMovie(movieId);

        Movie movie = showHelper.getMovie(movieId);
        return showHelper.showsToShowDto(showRepository.findByMovie(movie));
    }

    @Override
    public ResponseDto updateShow(Long cinemaId, String cinemaHallName, Long showId, ShowDto showDto) {
        showHelper.canUpdate(cinemaId, cinemaHallName, showId, showDto);

        Movie movie = showHelper.getMovie(showDto.getMovieId());
        Show show = showHelper.getShow(showId);

        show.setStartDateTime(showDto.getStartDateTime());
        show.setEndDateTime(showDto.getEndDateTime());
        show.setMovie(movie);
        showSeatRepository.findByShow(show).forEach(showSeat -> {
            showSeat.setPrice(showDto.getPrice());
            showSeatRepository.save(showSeat);
        });
        showRepository.save(show);
        return new ResponseDto(true, String.format("show of movie %s updated successfully", movie.getName()));
    }

    @Override
    public ResponseDto deleteShow(Long cinemaId, String cinemaHallName, Long showId) {
        showHelper.canDelete(cinemaId, cinemaHallName, showId);

        Show show = showHelper.getShow(showId);
        showSeatRepository.deleteAll(showSeatRepository.findByShow(show));
        showRepository.delete(show);

        return new ResponseDto(true, String.format("show of movie %s deleted successfully", show.getMovie().getName()));
    }

    @Override
    public ShowResponseDto getShow(Long cinemaId, String cinemaHallName, Long showId) {
        showHelper.checkShow(cinemaId, cinemaHallName, showId);
        Show show = showHelper.getShow(showId);
        return new ShowResponseDto(show);
    }


}