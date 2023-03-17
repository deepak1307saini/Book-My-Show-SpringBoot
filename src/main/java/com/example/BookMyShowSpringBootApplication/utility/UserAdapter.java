/**
 * 
 */
package com.example.BookMyShowSpringBootApplication.utility;

import com.example.BookMyShowSpringBootApplication.dto.UserDto;
import com.example.BookMyShowSpringBootApplication.entity.User;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserAdapter {

	public static User toEntity(UserDto userDto,String password) {

		return User.builder()
				.username(userDto.getUsername())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(password)
				.activeStatus(false)
				.localDateTime(LocalDateTime.now())
				.build();

	}

	public static void upadateUser(User oldUser,User newUser){
		oldUser.setName(newUser.getName());
		oldUser.setLocalDateTime(newUser.getLocalDateTime());
		oldUser.setPassword(newUser.getPassword());
		oldUser.setUsername(newUser.getUsername());
		oldUser.setOtp(newUser.getOtp());
	}

}