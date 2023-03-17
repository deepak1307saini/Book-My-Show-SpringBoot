package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.MovieResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.Movie;
import com.example.BookMyShowSpringBootApplication.helper.SearchHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Data
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;

    private final static String GLOBAL_SEARCH_URL = "http://localhost:8083/search/";

    public List<MovieResponseDto> searchMovie(String keyword) {
        String url = GLOBAL_SEARCH_URL + keyword;
        ResponseEntity<MovieResponseDto[]> response = restTemplate.getForEntity(url, MovieResponseDto[].class);
        if (response.getBody() == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(response.getBody());
    }

}
