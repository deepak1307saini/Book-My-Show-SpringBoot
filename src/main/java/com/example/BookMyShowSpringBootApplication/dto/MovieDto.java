/**
 *
 */
package com.example.BookMyShowSpringBootApplication.dto;


import java.util.Date;
import java.util.List;

import com.example.BookMyShowSpringBootApplication.enums.CertificateType;
import com.example.BookMyShowSpringBootApplication.enums.Genre;
import com.example.BookMyShowSpringBootApplication.enums.Language;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class MovieDto {
    private long id;

    private String name;

    private String description;


    private Language language;


    private CertificateType certificateType;


    private Date releaseDate;

    private Genre genre;


    private List<String> actorNames;

    public MovieDto(long movieId){
        this.id=movieId;
    }

}