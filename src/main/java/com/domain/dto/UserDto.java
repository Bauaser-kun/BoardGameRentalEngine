package com.domain.dto;

import com.domain.RentedGame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private LocalDate registeredOn;
    private String userLevel;
}
