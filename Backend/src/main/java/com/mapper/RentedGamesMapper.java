package com.mapper;

import com.domain.Rent;
import com.domain.dto.RentDto;
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

    public Rent mapToRentedGame(RentDto rentDto) {
        return new Rent(rentDto.getId(), gameMapper.mapToGame(rentDto.getGame()), userMapper.mapToUser(rentDto.getUser()),
                rentDto.getRentedOn(), rentDto.getReturnDate());
    }

    public RentDto mapToRentedGameDto(Rent rent) {
        return new RentDto(rent.getId(), gameMapper.mapToGameDto(rent.getGame()), userMapper.mapToUserDto(rent.getUser()),
                rent.getRentedOn(), rent.getReturnDate());
    }

    public List<Rent> mapToRentedGameList(final List<RentDto> rentDtos) {
        return rentDtos.stream().map(this::mapToRentedGame).collect(Collectors.toList());
    }

    public List<RentDto> mapToRentedGameDtoList(final List<Rent> rents) {
        return rents.stream().map(this::mapToRentedGameDto).collect(Collectors.toList());
    }
}
