package com.service;

import com.domain.*;
import com.domain.dto.BoardGameDto;
import com.domain.dto.OrderDto;
import com.domain.dto.RentDto;
import com.domain.dto.UserDto;
import com.exceptions.*;
import com.mapper.BoardGameMapper;
import com.mapper.OrderMapper;
import com.mapper.RentedGamesMapper;
import com.mapper.UserMapper;
import com.service.facade.DatabasesFacade;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardGameDbTestSuite {
    @Autowired
    BoardGameDbService service;

    @Autowired
    BoardGameMapper mapper;

    @Test
    void shouldSaveGame() throws GameNotFoundException {
        //Given
        BoardGame game = new BoardGame("game test", 15.5, 5);

        //When
        service.saveGame(game);

        //Then
        long id = game.getId();
        Optional<BoardGame> readGame = service.getGame(id);
        assertTrue(readGame.isPresent());

        //Cleanup
        service.deleteGame(id);
    }

    @Test
    void shouldGetAllBoardGames() {
        //Given
        BoardGame game = new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2);
        BoardGame game2 = new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6);
        BoardGame game3 = new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10);
        BoardGame game4 = new BoardGame("Ankh-Morpork", MechanicType.CARD, 15.75,5);
        BoardGame game5 = new BoardGame("Munchkin", MechanicType.CARD, 5, 25);
        BoardGame game6 = new BoardGame("Munchkin Gloomie", MechanicType.CARD, 6.50, 10);
        BoardGame game7 = new BoardGame("Valhalla", MechanicType.DICE, 9.99, 6);
        List<BoardGame> games = List.of(game, game2, game3, game4, game5, game6, game7);

        for (BoardGame gameToSave : games) {
            service.saveGame(gameToSave);
        }

        //When
        List<BoardGame> readGames = service.getAllGames();

        //Then
        assertEquals(7, readGames.size());
        assertTrue(readGames.stream().anyMatch(g -> g.getTitle().equals(game4.getTitle())));

        //Cleanup
        for (BoardGame gameToDelete : games) {
            service.deleteGame(gameToDelete.getId());
        }
    }

    @Test
    void shouldGetGameById() {
        //Given
        BoardGame game = new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2);
        BoardGame game2 = new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6);
        BoardGame game3 = new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10);
        List<BoardGame> games = List.of(game, game2, game3);

        for (BoardGame gameToSave : games) {
            service.saveGame(gameToSave);
        }

        //When
        Long id = game2.getId();
        Optional<BoardGame> readGame = service.getGame(id);

        //Then
        assertEquals("Gloomhaven edycja Polska", readGame.get().getTitle());

        //Cleanup
        for (BoardGame gameToDelete : games) {
            service.deleteGame(gameToDelete.getTitle());
        }
    }

    @Test
    void shouldGetGameByTitle() {
        //Given
        BoardGame game = new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2);
        BoardGame game2 = new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6);
        BoardGame game3 = new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10);
        List<BoardGame> games = List.of(game, game2, game3);

        for (BoardGame gameToSave : games) {
            service.saveGame(gameToSave);
        }

        //When
        String title = game.getTitle();
        Optional<BoardGame> readGame = service.getGame(title);

        //Then
        assertEquals(2, readGame.get().getCopies());

        //Cleanup
        for (BoardGame gameToDelete : games) {
            service.deleteGame(gameToDelete.getTitle());
        }
    }

    @Test
    void shouldFindGameByMechanicType() {
        //Given
        BoardGame game = new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2);
        BoardGame game2 = new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6);
        BoardGame game3 = new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10);
        BoardGame game4 = new BoardGame("Ankh-Morpork", MechanicType.CARD, 15.75,5);
        BoardGame game5 = new BoardGame("Munchkin", MechanicType.CARD, 5, 25);
        BoardGame game6 = new BoardGame("Munchkin Gloomie", MechanicType.CARD, 6.50, 10);
        BoardGame game7 = new BoardGame("Valhalla", MechanicType.DICE, 9.99, 6);
        List<BoardGame> games = List.of(game, game2, game3, game4, game5, game6, game7);

        for (BoardGame gameToSave : games) {
            service.saveGame(gameToSave);
        }

        //When
        List<BoardGame> readGames = service.getGamesWithMechanic("card");

        //Then
        assertEquals(3, readGames.size());
        assertTrue(readGames.stream().anyMatch(g -> g.getTitle().equals(game4.getTitle())));

        //Cleanup
        for (BoardGame gameToDelete : games) {
            service.deleteGame(gameToDelete.getTitle());
        }
    }

    @Test
    void shouldFindGameByTitle() {
        //Given
        BoardGame game = new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2);
        BoardGame game2 = new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6);
        BoardGame game3 = new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10);
        BoardGame game4 = new BoardGame("Ankh-Morpork", MechanicType.CARD, 15.75,5);
        BoardGame game5 = new BoardGame("Munchkin", MechanicType.CARD, 5, 25);
        BoardGame game6 = new BoardGame("Munchkin Gloomie", MechanicType.CARD, 6.50, 10);
        BoardGame game7 = new BoardGame("Valhalla", MechanicType.DICE, 9.99, 6);
        List<BoardGame> games = List.of(game, game2, game3, game4, game5, game6, game7);

        for (BoardGame gameToSave : games) {
            service.saveGame(gameToSave);
        }

        //When
        List<BoardGame> readGames = service.getGamesWithTitle("munch");

        //Then
        assertEquals(2, readGames.size());
        assertTrue(readGames.stream().anyMatch(g -> g.getType().equals(game5.getType())));

        //Cleanup
        for (BoardGame gameToDelete : games) {
            service.deleteGame(gameToDelete.getTitle());
        }
    }
}
