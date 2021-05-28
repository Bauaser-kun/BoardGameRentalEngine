package com.mapper;

import com.domain.BoardGame;
import com.domain.MechanicType;
import com.domain.dto.BoardGameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameMapper {
    @Autowired
    private RentedGamesMapper gamesMapper;

    @Autowired
    private OrderMapper orderMapper;

    public BoardGame mapToGame(BoardGameDto boardGameDto) {
        return new BoardGame(boardGameDto.getId(), boardGameDto.getTitle(), Enum.valueOf(MechanicType.class, boardGameDto.getType()),
                boardGameDto.getPrice(), boardGameDto.getCopies(), gamesMapper.mapToRentedGameList(boardGameDto.getRentedGames()),
                orderMapper.mapToOrder(boardGameDto.getOrder()));
    }

    public BoardGameDto mapToGameDto(BoardGame boardGame){
        return new BoardGameDto(boardGame.getId(), boardGame.getTitle(), boardGame.getType().toString(),
                boardGame.getPrice(), boardGame.getCopies(), gamesMapper.mapToRentedGameDtoList(boardGame.getRentedGames()),
                orderMapper.mapToOrderDto(boardGame.getOrder()));
    }

    public List<BoardGame> mapToGameList(final List<BoardGameDto> gameDtos) {
        return gameDtos.stream().map(this::mapToGame).collect(Collectors.toList());
    }

    public List<BoardGameDto> mapToGameDtoList(final List<BoardGame> games) {
        return games.stream().map(this::mapToGameDto).collect(Collectors.toList());
    }
}
