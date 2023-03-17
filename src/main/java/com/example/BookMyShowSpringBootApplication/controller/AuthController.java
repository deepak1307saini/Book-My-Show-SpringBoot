package com.example.BookMyShowSpringBootApplication.controller;

import com.example.BookMyShowSpringBootApplication.dto.*;
import com.example.BookMyShowSpringBootApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseDto signUp(@RequestBody UserDto userDto) {
        return authService.signUp(userDto);
    }

    @PostMapping("/verifyRegistration")
    public ResponseDto verifyRegistration(@RequestBody OtpDTO otpDTO) {
        return authService.verify(otpDTO);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/changePassword")
    public ResponseDto changePassword(@RequestBody PasswordDto passwordDto) {
        return authService.changePassword(passwordDto);

    }

    @PostMapping("/forgetPassword")
    public ResponseDto forgetPassword(@RequestParam("email") String email, HttpServletRequest request) {
        return authService.forgetPassword(email, getApplicationUrl(request));
    }

    @PostMapping("/verify")
    public ResponseDto verify(@RequestBody VerificationRequestDto verificationRequestDto, @RequestParam("token") String token) {
        return authService.verify(verificationRequestDto, token);
    }

    public String getApplicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
