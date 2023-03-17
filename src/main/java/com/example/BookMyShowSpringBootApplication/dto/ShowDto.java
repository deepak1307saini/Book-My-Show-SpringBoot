/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class ShowDto {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date startDateTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date endDateTime;

    @NotNull
    private Long movieId;

    @NotNull
    @Positive
    private Integer price;
}