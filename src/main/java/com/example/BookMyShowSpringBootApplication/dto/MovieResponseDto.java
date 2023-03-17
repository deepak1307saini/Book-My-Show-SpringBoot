package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.Actor;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.enums.CertificateType;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MovieResponseDto {
    private Long id;
    private String name;
    private String description;
    private Language language;
    private Date releaseDate;
    private CertificateType certificateType;
    private Genre genre;
    private List<String> actors = new ArrayList<>();

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.language = movie.getLanguage();
        this.releaseDate = movie.getReleaseDate();
        this.certificateType = movie.getCertificateType();
        this.genre = movie.getGenre();
        this.actors = movie
                .getActors()
                .stream()
                .map(Actor::getName)
                .collect(Collectors.toList());
    }

}
