package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookMyShowSpringBootApplication.entity.Movie;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByNameContaining(String partialMovieName);

    boolean existsByNameAndLanguage(String name, Language language);

    List<Movie> findByGenre(Genre genre);

    List<Movie> findByLanguage(Language language);

}