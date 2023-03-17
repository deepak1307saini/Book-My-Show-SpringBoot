package com.example.BookMyShowSpringBootApplication.entity;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    private String username;

    @Length(min = 3)
    private String name;

    @Email
    private String email;
    private String password;
    private String token;
    private Long otp;
    private Boolean activeStatus;
    private LocalDateTime localDateTime;
}
