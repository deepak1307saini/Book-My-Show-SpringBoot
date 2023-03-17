package com.example.BookMyShowSpringBootApplication.helper;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.example.BookMyShowSpringBootApplication.dto.MovieResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Actor;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.enums.CertificateType;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;
import com.example.BookMyShowSpringBootApplication.repository.ActorRepository;
import com.example.BookMyShowSpringBootApplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.BookMyShowSpringBootApplication.exception.DuplicateRecordException;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieHelper {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    public void checkGenre(String genre) {
        try {
            Genre.valueOf(genre.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("invalid Genre type, select genre from %s",
                    Arrays.toString(Genre.class.getEnumConstants())));
        }
    }

    public void checkLanguage(String language) {
        try {
            Language.valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("invalid Language type, select language from %s",
                    Arrays.toString(Language.class.getEnumConstants())));
        }
    }

    public void checkCertificateType(String certificateType) {
        try {
            CertificateType.valueOf(certificateType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("invalid Certificate type, select CertificateType from %s",
                    Arrays.toString(CertificateType.class.getEnumConstants())));
        }
    }

    public void mapMovieRequestToMovie(MovieDto movieDto, Movie movie) {
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setLanguage(movieDto.getLanguage());
        movie.setGenre(movieDto.getGenre());
        movie.setCertificateType(movieDto.getCertificateType());
        movie.setActors(createActorSet(movieDto.getActorNames()));
    }

    private List<Actor> createActorSet(List<String> actorNames) {
        return actorNames.stream()
                .map(actorName -> {
                    if (!actorRepository.existsByName(actorName)) {
                        actorRepository.save(new Actor(actorName));
                    }
                    return actorRepository.findByName(actorName).get();
                })
                .collect(Collectors.toList());
    }

    public void canAdd(MovieDto movieDto) {
        if (movieRepository.existsByNameAndLanguage(movieDto.getName(), movieDto.getLanguage())) {
            throw new DuplicateRecordException(String.format("Movie Already Exists with Name: " + movieDto.getName() + " in Language: " + movieDto.getLanguage()));
        }
    }

    public void checkMovie(Long id) {
        if (!movieRepository.existsById(id))
            throw new EntityNotFoundException("invalid movie id");
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId).get();
    }


    public void canAdd(String genre) {
        checkGenre(genre);
    }

    public void canUpdate(Long movieId) {
        checkMovie(movieId);
    }


    public void canDelete(Long movieId) {
        checkMovie(movieId);
    }

    public List<MovieResponseDto> moviesToMovieDto(List<Movie> movies) {
        return movies
                .stream()
                .map(movie -> new MovieResponseDto(movie))
                .collect(Collectors.toList());
    }
}
