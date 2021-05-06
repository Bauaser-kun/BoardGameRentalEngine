package com.domain.dto;

import com.domain.RentedGame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGameDto {
    private Long id;
    private String title;
    private String type;
    private double price;
    private int copies;
    private List<RentedGame> rentedGames;
}
