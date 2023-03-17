package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.repository.ActorRepository;
import com.example.BookMyShowSpringBootApplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SearchHelper {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    public List<Movie> searchMovies(String keyword){
        Set<Movie> movies=new HashSet<>();
        List genres = Arrays.asList(Genre.values());

        movies.addAll(movieRepository.findByNameContaining(keyword));

        if(Stream.of(Genre.values()).anyMatch(v -> v.name().equals(keyword.toUpperCase()))){
            movies.addAll(movieRepository.findByGenre(Genre.valueOf(keyword.toUpperCase())));
        }

        actorRepository.findByNameContaining(keyword)
                .forEach(actor -> movies.addAll(actor.getMovies()));

        return movies.stream().collect(Collectors.toList());

    }

}
