package com.example.BookMyShowSpringBootApplication.utility;

import com.example.BookMyShowSpringBootApplication.entity.EmailDetails;


public class GetEmailDetailInstance {

    public static EmailDetails getInstance(String email,Long otp){
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject("Account Verification");
        emailDetails.setMsgBody("OTP for your Account Verification: "+otp);
        return emailDetails;
    }

}
