package com.service.facade;

import com.domain.BoardGame;
import com.domain.dto.BoardGameDto;
import com.exceptions.GameNotFoundException;
import com.mapper.BoardGameMapper;
import com.mapper.OrderMapper;
import com.mapper.RentedGamesMapper;
import com.mapper.UserMapper;
import com.service.BoardGameDbService;
import com.service.OrderDbService;
import com.service.RentedGameDbService;
import com.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class DatabasesFacade {
    @Autowired
    BoardGameDbService boardGameService;

    @Autowired
    BoardGameMapper boardGameMapper;

    @Autowired
    OrderDbService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RentedGameDbService rentedGameService;

    @Autowired
    RentedGamesMapper rentedGamesMapper;

    @Autowired
    UserDbService userService;

    @Autowired
    UserMapper userMapper;

    public List<BoardGameDto> getAllBoardGames() {
        return boardGameMapper.mapToGameDtoList(boardGameService.getAllGames());
    }

    public BoardGameDto getBoardGame(final String title) throws GameNotFoundException {
        return boardGameMapper.mapToGameDto(boardGameService.getGame(title).orElseThrow(GameNotFoundException::new));
    }

    public BoardGameDto getBoardGame(final Long gameId) throws GameNotFoundException {
        return boardGameMapper.mapToGameDto(boardGameService.getGame(gameId).orElseThrow(GameNotFoundException::new));
    }

    public void createGame(final BoardGameDto boardGameDto) {
        boardGameService.saveGame(boardGameMapper.mapToGame(boardGameDto));
    }

    public void deleteGame(final Long gameId) {
        boardGameService.deleteGame(gameId);
    }

    public void deleteGame(final String title) {
        boardGameService.deleteGame(title);
    }

    public BoardGameDto updateGame(final BoardGameDto boardGameDto) {
        BoardGame savedGame = boardGameService.saveGame(boardGameMapper.mapToGame(boardGameDto));
        return boardGameMapper.mapToGameDto(savedGame);
    }
}
