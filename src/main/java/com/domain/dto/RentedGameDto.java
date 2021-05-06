package com.domain.dto;

import com.domain.BoardGame;
import com.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentedGameDto {
    private Long id;
    private BoardGame game;
    private User user;
    private LocalDate rentedOn;
    private LocalDate returnDate;
}
