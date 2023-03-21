package com.example.BookMyShowSpringBootApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDto {

    @Min(1)
    private long userId;

    @NotBlank
    private String comment;

    @Min(1) @Max(10)
    private int movieRating;
}
