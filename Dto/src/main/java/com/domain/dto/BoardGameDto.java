package com.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String type;
    private double price;
    @JsonIgnore
    private int copies;
    @JsonIgnore
    private List<RentDto> rentedGames;
    @JsonIgnore
    private OrderDto order;
}
