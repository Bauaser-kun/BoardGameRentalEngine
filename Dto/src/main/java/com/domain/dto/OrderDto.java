package com.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private UserDto user;
    private List<BoardGameDto> games;
    private LocalDate createdOn;
    private LocalDate rentedTill;

    public OrderDto(Long id, UserDto user, List<BoardGameDto> games) {
        this.id = id;
        this.user = user;
        this.games = games;
    }
}
