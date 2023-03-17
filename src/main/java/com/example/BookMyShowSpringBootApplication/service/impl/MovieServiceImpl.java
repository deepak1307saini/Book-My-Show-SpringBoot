
package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.example.BookMyShowSpringBootApplication.dto.MovieResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;
import com.example.BookMyShowSpringBootApplication.helper.MovieHelper;
import com.example.BookMyShowSpringBootApplication.repository.ActorRepository;
import com.example.BookMyShowSpringBootApplication.repository.MovieRepository;
import com.example.BookMyShowSpringBootApplication.service.MovieService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieHelper movieHelper;

    @Autowired
    KafkaTemplate<String, MovieDto> template;

    @Override
    public MovieResponseDto addMovie(MovieDto movieDto) {
        movieHelper.canAdd(movieDto);
        Movie movie = new Movie();
        movieHelper.mapMovieRequestToMovie(movieDto, movie);
        movieRepository.save(movie);
        movieDto.setId(movie.getId());
        template.send("movies","add",movieDto);
        return new MovieResponseDto(movie);
    }

    @Override
    public List<MovieResponseDto> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movieRepository.findAll()
                .forEach(movies::add);

        return movieHelper.moviesToMovieDto(movies);
    }

    @Override
    public List<MovieResponseDto> getByActorName(String actorName) {
        Set<Movie> movies = new HashSet<>();

        actorRepository.findByNameContaining(actorName)
                .forEach(actor -> movies.addAll(actor.getMovies()));

        return movieHelper.moviesToMovieDto(movies.stream().collect(Collectors.toList()));
    }

    @Override
    public List<MovieResponseDto> getByGenre(Genre genre) {
        List<Movie> movies = movieRepository.findByGenre(genre);
        return movieHelper.moviesToMovieDto(movies);
    }

    @Override
    public MovieResponseDto getMovie(Long movieId) {
        movieHelper.checkMovie(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        return new MovieResponseDto(movie);
    }

    @Override
    public ResponseDto updateMovie(Long movieId, MovieDto movieDto) {
        movieHelper.canUpdate(movieId);

        Movie movie = movieHelper.getMovie(movieId);
        movieHelper.mapMovieRequestToMovie(movieDto, movie);
        movieRepository.save(movie);
        movieDto.setId(movieId);
        template.send("movies","update",movieDto);
        return new ResponseDto(true, String.format("movie %s updated successfully", movie.getName()));
    }

    public List<MovieResponseDto> getByMovieName(String partialMovieName) {
        List<Movie> movies = movieRepository.findByNameContaining(partialMovieName);
        return movieHelper.moviesToMovieDto(movies);
    }

    public List<MovieResponseDto> getByLanguage(Language language) {
        List<Movie> movies = movieRepository.findByLanguage(language);
        return movieHelper.moviesToMovieDto(movies);
    }

    @Override
    public ResponseDto deleteMovie(Long movieId) {
        movieHelper.canDelete(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        template.send("movies","delete",new MovieDto(movieId));
        movieRepository.delete(movie);
        return new ResponseDto(true, String.format("movie %s deleted successfully", movie.getName()));
    }

}