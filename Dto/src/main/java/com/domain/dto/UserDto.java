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

    public UserDto(Long id, String username, String password, String name, String surname, String userLevel, String email, boolean isLogged, List<OrderDto> orders) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userLevel = userLevel;
        this.email = email;
        this.isLogged = isLogged;
        this.orders = orders;
    }
}
