package com.controller;

import com.domain.BoardGame;
import com.domain.dto.BoardGameDto;
import com.exceptions.GameNotFoundException;
import com.mapper.BoardGameMapper;
import com.service.BoardGameDbService;
import com.service.facade.DatabasesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/V1/Games/")
public class GameController {
    @Autowired
    DatabasesFacade facade;

    @GetMapping(value = "getGames")
    public List<BoardGameDto> getGames(){
        return facade.getAllBoardGames();
    }

    @GetMapping(value = "getGame")
    public BoardGameDto getGame(@RequestParam(required = false) Long gameId, @RequestParam(required = false) String title) throws GameNotFoundException {
        if (gameId == null) {
            return facade.getBoardGame(title);
        } else {
            return facade.getBoardGame(gameId);
        }
    }

    @PostMapping(value = "createGame")
    public void createGame(@RequestBody BoardGameDto boardGameDto) {
        facade.createGame(boardGameDto);
    }

    @DeleteMapping(value = "deleteGame")
    public void deleteGame(@RequestParam(required = false) Long gameId, @RequestParam(required = false) String title) throws GameNotFoundException {
        try {
            if (gameId == null) {
                facade.deleteGame(title);
            } else {
                facade.deleteGame(gameId);
            }
        } catch (IllegalArgumentException e) {
            throw new GameNotFoundException();
        }
    }

    @PutMapping(value = "updateGame")
    public BoardGameDto updateGame(@RequestBody BoardGameDto boardGameDto) {
        return facade.updateGame(boardGameDto);
    }
}
