package com.mapper;

import com.domain.BoardGame;
import com.domain.dto.BoardGameDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameMapper {
    public BoardGame mapToGame(BoardGameDto boardGameDto) {
        return new BoardGame(boardGameDto.getId(), boardGameDto.getTitle(), boardGameDto.getType(),
                boardGameDto.getPrice(), boardGameDto.getCopies(), boardGameDto.getRentedGames());
    }

    public BoardGameDto mapToGameDto(BoardGame boardGame){
        return new BoardGameDto(boardGame.getId(), boardGame.getTitle(), boardGame.getType(),
                boardGame.getPrice(), boardGame.getCopies(), boardGame.getRentedGames());
    }

    public List<BoardGame> gameList(final List<BoardGameDto> gameDtos) {
        return gameDtos.stream().map(this::mapToGame).collect(Collectors.toList());
    }

    public List<BoardGameDto> gameDtos(final List<BoardGame> games) {
        return games.stream().map(this::mapToGameDto).collect(Collectors.toList());
    }
}