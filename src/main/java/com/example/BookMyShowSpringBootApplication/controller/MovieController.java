package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.example.BookMyShowSpringBootApplication.dto.MovieResponseDto;
import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;
import com.example.BookMyShowSpringBootApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public MovieResponseDto add(@RequestBody MovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }

    @GetMapping(params = "movieName")
    public List<MovieResponseDto> getByMovieName(@RequestParam String movieName) {
        return movieService.getByMovieName(movieName);
    }

    @GetMapping("/para")
    public List<MovieResponseDto> getByActorName(@RequestParam String actorName) {
        return movieService.getByActorName(actorName);
    }

    @GetMapping(params = "genre")
    public List<MovieResponseDto> getByGenre(@RequestParam Genre genre) {
        return movieService.getByGenre(genre);
    }

    @GetMapping(params = "language")
    public List<MovieResponseDto> getByGenre(@RequestParam Language language) {
        return movieService.getByLanguage(language);
    }

    @GetMapping("/{movieId}")
    public MovieResponseDto getMovie(@PathVariable Long movieId) {
        return movieService.getMovie(movieId);
    }

    @GetMapping
    public List<MovieResponseDto> getMovies() {
        return movieService.getMovies();
    }

    @DeleteMapping("/{movieId}")
    public ResponseDto deleteMovie(@PathVariable Long movieId) {
        return movieService.deleteMovie(movieId);
    }

    @PutMapping("/{movieId}")
    public ResponseDto updateMovie(@PathVariable long movieId,@RequestBody MovieDto movieDto){
        return movieService.updateMovie(movieId,movieDto);
    }
}
