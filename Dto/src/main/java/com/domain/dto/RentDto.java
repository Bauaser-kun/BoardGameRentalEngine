package com.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Long id;
    private BoardGameDto game;
    private UserDto user;
    private LocalDate rentedOn;
    private LocalDate returnDate;
}
