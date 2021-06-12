package com.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardGameDto {
    private Long id;
    private String title;
    private String type;
    private double price;
    private int copies;
    private List<RentDto> rentedGames;
    private OrderDto order;
}
