package com.controller;

import com.controller.exceptions.GameNotFoundException;
import com.domain.BoardGame;
import com.domain.dto.BoardGameDto;
import com.mapper.BoardGameMapper;
import com.service.BoardGameDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/V1/Games/")
public class GameController {
    @Autowired
    BoardGameMapper mapper;

    @Autowired
    BoardGameDbService dbService;

    @GetMapping(value = "getGames")
    public List<BoardGameDto> getGames(){
        return mapper.mapToGameDtoList(dbService.getAllGames());
    }

    @GetMapping(value = "getGame")
    public BoardGameDto getGame(@RequestParam(required = false) Long gameId, @RequestParam(required = false) String title) throws GameNotFoundException {
        if (gameId == null) {
            return mapper.mapToGameDto(dbService.getGame(gameId).orElseThrow(GameNotFoundException::new));
        } else {
            return mapper.mapToGameDto(dbService.getGame(title).orElseThrow(GameNotFoundException::new));
        }
    }

    @PostMapping(value = "createGame")
    public void createGame(@RequestBody BoardGameDto boardGameDto) {
        dbService.saveGame(mapper.mapToGame(boardGameDto));
    }

    @DeleteMapping(value = "deleteGame")
    public void deleteGame(@RequestParam(required = false) Long gameId, @RequestParam(required = false) String title) throws GameNotFoundException {
        try {
            if (gameId == null) {
                dbService.deleteGame(title);
            } else {
                dbService.deleteGame(gameId);
            }

        } catch (IllegalArgumentException e) {
            throw new GameNotFoundException();
        }
    }

    @PutMapping(value = "updateGame")
    public BoardGameDto updateGame(@RequestBody BoardGameDto boardGameDto) {
        BoardGame savedGame = dbService.saveGame(mapper.mapToGame(boardGameDto));
        return mapper.mapToGameDto(savedGame);
    }
}
