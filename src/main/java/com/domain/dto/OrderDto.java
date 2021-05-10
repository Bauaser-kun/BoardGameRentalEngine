package com.domain.dto;

import com.domain.BoardGame;
import com.domain.User;
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
    private User user;
    private List<BoardGame> games;
    private LocalDate createdOn;
    private LocalDate rentedTill;
}
