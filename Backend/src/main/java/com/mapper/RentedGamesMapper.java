package com.mapper;

import com.domain.RentedGame;
import com.domain.dto.RentedGameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentedGamesMapper {
    @Autowired
    private BoardGameMapper gameMapper;

    @Autowired
    private UserMapper userMapper;

    public RentedGame mapToRentedGame(RentedGameDto rentedGameDto) {
        return new RentedGame(rentedGameDto.getId(), gameMapper.mapToGame(rentedGameDto.getGame()), userMapper.mapToUser(rentedGameDto.getUser()),
                rentedGameDto.getRentedOn(), rentedGameDto.getReturnDate());
    }

    public RentedGameDto mapToRentedGameDto(RentedGame rentedGame) {
        return new RentedGameDto(rentedGame.getId(), gameMapper.mapToGameDto(rentedGame.getGame()), userMapper.mapToUserDto(rentedGame.getUser()),
                rentedGame.getRentedOn(), rentedGame.getReturnDate());
    }

    public List<RentedGame> mapToRentedGameList(final List<RentedGameDto> rentedGameDtos) {
        return rentedGameDtos.stream().map(this::mapToRentedGame).collect(Collectors.toList());
    }

    public List<RentedGameDto> mapToRentedGameDtoList(final List<RentedGame> rentedGames) {
        return rentedGames.stream().map(this::mapToRentedGameDto).collect(Collectors.toList());
    }
}
