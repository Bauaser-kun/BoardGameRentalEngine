package com.mapper;

import com.domain.RentedGame;
import com.domain.dto.RentedGameDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentedGamesMapper {
    public RentedGame mapToRentedGame(RentedGameDto rentedGameDto) {
        return new RentedGame(rentedGameDto.getId(), rentedGameDto.getGame(), rentedGameDto.getUser(),
                rentedGameDto.getRentedOn(), rentedGameDto.getReturnDate());
    }

    public RentedGameDto mapToRentedGameDto(RentedGame rentedGame) {
        return new RentedGameDto(rentedGame.getId(), rentedGame.getGame(), rentedGame.getUser(),
                rentedGame.getRentedOn(), rentedGame.getReturnDate());
    }

    public List<RentedGame> rentedGames(final List<RentedGameDto> rentedGameDtos) {
        return rentedGameDtos.stream().map(this::mapToRentedGame).collect(Collectors.toList());
    }

    public List<RentedGameDto> rentedGameDtos(final List<RentedGame> rentedGames) {
        return rentedGames.stream().map(this::mapToRentedGameDto).collect(Collectors.toList());
    }
}
