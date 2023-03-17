package com.example.BookMyShowSpringBootApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequestDto {
    private String email;
    private String newPassword;
}
