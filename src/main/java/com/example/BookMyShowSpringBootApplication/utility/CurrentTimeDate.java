package com.example.BookMyShowSpringBootApplication.utility;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class CurrentTimeDate {
    public static boolean timeDiff(LocalDateTime before){
        LocalDateTime now=LocalDateTime.now();
        long diff = SECONDS.between(before,now);
        System.out.println(diff);
        if (diff>60)
            return true;
        return false;
    }
}
