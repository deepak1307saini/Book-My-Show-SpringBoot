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
    @Max(10)
    @Min(1)
    private Integer movieRating;
}
