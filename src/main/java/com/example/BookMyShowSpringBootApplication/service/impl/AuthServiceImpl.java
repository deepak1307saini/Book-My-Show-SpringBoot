package com.example.BookMyShowSpringBootApplication.service.impl;

import com.example.BookMyShowSpringBootApplication.dto.*;
import com.example.BookMyShowSpringBootApplication.entity.User;
import com.example.BookMyShowSpringBootApplication.repository.UserRepository;
import com.example.BookMyShowSpringBootApplication.service.AuthService;
import com.example.BookMyShowSpringBootApplication.service.EmailService;
import com.example.BookMyShowSpringBootApplication.service.UserService;
import com.example.BookMyShowSpringBootApplication.utility.CurrentTimeDate;
import com.example.BookMyShowSpringBootApplication.utility.GetEmailDetailInstance;
import com.example.BookMyShowSpringBootApplication.utility.UserAdapter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Data
public class AuthServiceImpl implements AuthService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public ResponseDto signUp(UserDto userDto) {
        User user1 = userService.getUserByEmail(userDto.getEmail());
        if (user1 != null && user1.getActiveStatus()) {
            return new ResponseDto(false, "User already exists");
        }

        User user= UserAdapter.toEntity(userDto,passwordEncoder.encode(userDto.getPassword()));

        //generate
        Random rand = new Random();
        Long OTP = (long) (rand.nextInt(9000) + 1000);
        user.setOtp(OTP);

        // send mail
        emailService.sendSimpleMail(GetEmailDetailInstance.getInstance(user.getEmail(), user.getOtp()));

        if (user1 != null) {
            UserAdapter.upadateUser(user1,user);
            userRepository.save(user1);
        } else
            userRepository.save(user);

        return new ResponseDto(true, String.format("OTP sent to email : %s", userDto.getEmail()));
    }

    @Override
    public ResponseDto verify(OtpDTO otpDTO) {
        User user = userRepository.findByUsername(otpDTO.getUsername());

        if (CurrentTimeDate.timeDiff(user.getLocalDateTime())) {
            return new ResponseDto(false, "Time out, resend otp");
        }
        if (!user.getOtp().equals(otpDTO.getOtp())) {
            return new ResponseDto(false, "Otp not matched");
        }

        user.setActiveStatus(true);
        userRepository.save(user);
        return new ResponseDto(true, "User Verified");
    }

    @Override
    public ResponseDto login(LoginRequestDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null && !user.getActiveStatus()) {
            return new ResponseDto(false, "email not registered!");
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new ResponseDto(false, "invalid credentials!");
        }
        String token = UUID.randomUUID().toString();
        return new ResponseDto(true, String.format("User name = %s ," +
                                "Email = %s ," +
                                "Name = %s", user.getUsername(), user.getEmail(), user.getName()));
    }

    @Override
    public ResponseDto changePassword(PasswordDto passwordDto) {
        User user = userRepository.findByEmail(passwordDto.getEmail());
        if (user == null) {
            return new ResponseDto(false, "email not registered!");
        }
        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            return new ResponseDto(false, "incorrect Credentials!");
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);
        return new ResponseDto(true, "password changed!");
    }

    @Override
    public ResponseDto forgetPassword(String email, String applicationUrl) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseDto(false, "email not registered!");
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);
        String url = applicationUrl + "/verify?token=" + token;
        return new ResponseDto(true, String.format("Email Verification : %s", url));
    }

    @Override
    public ResponseDto verify(VerificationRequestDto verificationRequestDto, String token) {
        User user = userRepository.findByEmail(verificationRequestDto.getEmail());
        if (user == null) {
            return new ResponseDto(false, "email not registered!");
        }
        if (!token.equals(user.getToken())) {
            return new ResponseDto(false, "token invalid!");
        }
        user.setPassword(passwordEncoder.encode(verificationRequestDto.getNewPassword()));
        userRepository.save(user);
        return new ResponseDto(true, "password changed successfully");


    }
}
