package com.example.BookMyShowSpringBootApplication.service;

import com.example.BookMyShowSpringBootApplication.dto.ResponseDto;
import com.example.BookMyShowSpringBootApplication.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUser();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    ResponseDto deleteUser(Long id);
}
