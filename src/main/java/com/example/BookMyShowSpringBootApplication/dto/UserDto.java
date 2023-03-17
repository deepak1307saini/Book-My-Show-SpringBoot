package com.example.BookMyShowSpringBootApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "username is Mandatory")
    private String username;

    @NotBlank(message = "name is Mandatory")
    private String name;

    @NotBlank(message = "email is Mandatory")
    private String email;

    @NotBlank(message = "password is Mandatory")
    private String password;
}
