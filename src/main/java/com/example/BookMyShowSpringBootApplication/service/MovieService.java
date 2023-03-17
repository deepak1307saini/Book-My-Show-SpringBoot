package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.example.BookMyShowSpringBootApplication.dto.MovieResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;

import java.util.List;

public interface MovieService {
    MovieResponseDto addMovie(MovieDto movieDto);

    List<MovieResponseDto> getMovies();

    List<MovieResponseDto> getByActorName(String actorName);

    List<MovieResponseDto> getByGenre(Genre genre);

    MovieResponseDto getMovie(Long movieId);

    ResponseDto updateMovie(Long movieId, MovieDto movieRequestDTO);

    ResponseDto deleteMovie(Long movieId);

    List<MovieResponseDto> getByMovieName(String partialMovieName);

    List<MovieResponseDto> getByLanguage(Language language);
}
