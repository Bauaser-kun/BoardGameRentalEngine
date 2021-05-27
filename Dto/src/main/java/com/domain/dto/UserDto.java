package com.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String userLevel;
    private String email;
    private LocalDate registeredOn;
    private List<RentedGameDto> rentedGames;
    private List<OrderDto> orders;
}
