package com.example.BookMyShowSpringBootApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(min = 3, max = 15)
    private String name;

    @Column(nullable = false)
    @Length(min = 3, max = 15)
    private String state;

    @Column(nullable = false)
    @Length(min = 6, max = 7)
    @Pattern(regexp = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$", message = "Invalid pin pattern")
    private String pinCode;

    public City(String name, String pinCode, String state) {
        this.name = name;
        this.pinCode = pinCode;
        this.state = state;
    }
}
