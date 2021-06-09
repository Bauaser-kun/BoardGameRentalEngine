package com.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String userLevel;
    private String email;
    private LocalDate registeredOn;
    private boolean isLogged;
    private List<OrderDto> orders;
}
