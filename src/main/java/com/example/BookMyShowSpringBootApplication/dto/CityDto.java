package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.City;
import lombok.Data;

@Data
public class CityDto {

    private String name;

    private String state;

    private String pinCode;

    public CityDto(City city) {
        this.name = city.getName();
        this.state = city.getState();
        this.pinCode = city.getPinCode();
    }

}
