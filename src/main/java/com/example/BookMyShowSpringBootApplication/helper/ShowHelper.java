package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowDto;
import com.example.BookMyShowSpringBootApplication.dto.ShowResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.*;
import com.example.BookMyShowSpringBootApplication.repository.CinemaHallSeatRepository;
import com.example.BookMyShowSpringBootApplication.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowHelper {

    @Autowired
    MovieHelper movieHelper;

    @Autowired
    CinemaHallHelper cinemaHallHelper;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    CinemaHallSeatRepository cinemaHallSeatRepository;

    public void checkMovie(Long movieId) {
        movieHelper.checkMovie(movieId);
    }

    public void checkCinemaHall(Long cinemaId, String cinemaHallName) {
        cinemaHallHelper.checkCinemaHall(cinemaId, cinemaHallName);
    }

    public void checkShowTime(Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            throw new IllegalArgumentException("start time should be before end time");
        }
    }

    public void checkCinemaHallAvalibility(Long cinemaId, String cinemaHallName, ShowDto showDto) {
        CinemaHall cinemaHall = cinemaHallHelper.getCinemaHall(cinemaId, cinemaHallName);

        List<Show> showList = cinemaHall.getShows();

        for (int i = 0; i < showList.size(); i++) {
            if (showList.get(i).getStartDateTime().after(showDto.getEndDateTime()) || showList.get(i).getEndDateTime().before(showDto.getStartDateTime())) {
                continue;
            } else {
                throw new IllegalArgumentException("Another show is there in this time period");
            }
        }
    }


    public void canAdd(Long cinemaId, String cinemaHallName, ShowDto showDto) {
        checkMovie(showDto.getMovieId());
        checkCinemaHall(cinemaId, cinemaHallName);
        checkShowTime(showDto.getStartDateTime(), showDto.getEndDateTime());

        checkCinemaHallAvalibility(cinemaId, cinemaHallName, showDto);

    }

    public CinemaHall getCinemaHall(Long cinemaId, String cinemaHallName) {
        return cinemaHallHelper.getCinemaHall(cinemaId, cinemaHallName);
    }

    public Movie getMovie(Long movieId) {
        return movieHelper.getMovie(movieId);
    }

    public ResponseDto mapShowRequestToShow(Show show, ShowDto showDto, Long cinemaId, String cinemaHallName) {
        CinemaHall cinemaHall = getCinemaHall(cinemaId, cinemaHallName);
        Movie movie = getMovie(showDto.getMovieId());

        show.setStartDateTime(showDto.getStartDateTime());
        show.setEndDateTime(showDto.getEndDateTime());
        show.setMovie(movie);
        show.setCinemaHall(cinemaHall);
        show.setShowSeats(cinemaHallSeatRepository.findByCinemaHall(cinemaHall)
                .stream()
                .map(cinemaHallSeat -> new ShowSeat(showDto.getPrice(), show, cinemaHallSeat))
                .collect(Collectors.toList()));

        showRepository.save(show);
        return new ResponseDto(true, String.format("Show of movie %s added successfully", movie.getName()));
    }

    public void checkShow(Long movieId, Long showId) {
        checkMovie(movieId);
        Movie movie = getMovie(movieId);
        if (!showRepository.existsByIdAndMovie(showId, movie))
            throw new EntityNotFoundException("Invalid show id");
    }

    public Show getShow(Long showId) {
        return showRepository.findById(showId).get();
    }

    public void canUpdate(Long cinemaId, String cinemaHallName, Long showId, ShowDto showDto) {
        checkShow(cinemaId, cinemaHallName, showId);
        checkMovie(showDto.getMovieId());
        checkShowTime(showDto.getStartDateTime(), showDto.getEndDateTime());

        CinemaHall cinemaHall = cinemaHallHelper.getCinemaHall(cinemaId, cinemaHallName);

        List<Show> showList = cinemaHall.getShows();

        for (int i = 0; i < showList.size(); i++) {
            if (showList.get(i).getStartDateTime().after(showDto.getEndDateTime()) || showList.get(i).getEndDateTime().before(showDto.getStartDateTime()) || showId.equals(showList.get(i).getId())) {
                continue;
            } else {
                throw new IllegalArgumentException("Another show is there in this time period");
            }
        }

    }

    public void checkShow(Long cinemaId, String cinemaHallName, Long showId) {
        checkCinemaHall(cinemaId, cinemaHallName);
        CinemaHall cinemaHall = getCinemaHall(cinemaId, cinemaHallName);
        if (!showRepository.existsByIdAndCinemaHall(showId, cinemaHall))
            throw new EntityNotFoundException("Invalid show id");
    }

    public void canDelete(Long cinemaId, String cinemaHallName, Long showId) {
        checkShow(cinemaId, cinemaHallName, showId);
    }

    public List<ShowResponseDto> showsToShowDto(List<Show> shows){
        return shows.stream().map(show -> new ShowResponseDto(show)).collect(Collectors.toList());
    }
}