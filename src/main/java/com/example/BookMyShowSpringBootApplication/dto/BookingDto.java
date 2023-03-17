/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.dto;

import java.util.List;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookingDto {

	@NotEmpty
	List<String> seatNumbers;

}