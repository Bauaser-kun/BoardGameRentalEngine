package com.service;

import com.domain.*;
import com.exceptions.NoCopiesAvailableException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTestSuite {
    @Autowired
    BoardGameDbService boardGameDbService;

    @Autowired
    RentedGameDbService rentedGameDbService;

    @Autowired
    UserDbService userDbService;

    @Autowired
    OrderDbService orderDbService;

    @Test
    void populateDatabaseWithSampleData() {
        boardGameDbService.saveGame(new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2));
        boardGameDbService.saveGame(new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6));
        boardGameDbService.saveGame(new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10));
        boardGameDbService.saveGame(new BoardGame("Ankh-Morpork", MechanicType.CARD, 15.75,5));
        boardGameDbService.saveGame(new BoardGame("Munchkin", MechanicType.CARD, 5, 25));
        boardGameDbService.saveGame(new BoardGame("Munchkin Gloomie", MechanicType.CARD, 6.50, 10));
        boardGameDbService.saveGame(new BoardGame("Valhalla", MechanicType.DICE, 9.99, 6));
    }

    @Test
    void shouldSaveGame(){
        //Given
        BoardGame game = new BoardGame("game test", 15.5, 5);

        //When
        boardGameDbService.saveGame(game);

        //Then
        long id = game.getId();
        Optional<BoardGame> readGame = boardGameDbService.getGame(id);
        assertTrue(readGame.isPresent());

        //Cleanup
        boardGameDbService.deleteGame(id);
        }

    @Test
    void shouldThrowNoCopiesException() {
        //Given & When
        BoardGame game = new BoardGame("game test", 15.5, 0);
        String title = game.getTitle();
        boardGameDbService.saveGame(game);

        //When & Then
        assertThrows(NoCopiesAvailableException.class, ()->
                rentedGameDbService.saveRentedGame(new Rent(boardGameDbService.getGame(title).get())));

        //CleanUp
        boardGameDbService.deleteGame(game.getTitle());
    }

    @SneakyThrows
    @Test
    void shouldCreateOrder(){
        //Given
        User user = new User("username", "password", "name", "surname", "level", "email", LocalDate.of(2021, 01, 01));
        userDbService.saveUser(user);
        BoardGame boardGame = new BoardGame("game test", 15.5, 5);
        boardGameDbService.saveGame(boardGame);
        Order order = new Order(user, List.of(boardGame));

        //When
        orderDbService.saveOrder(order);

        //Then
        Long id = order.getId();
        Optional<Order> readOrder = orderDbService.getOrder(id);
        assertTrue(readOrder.isPresent());

        //Cleanup
        orderDbService.deleteOrder(id);
        rentedGameDbService.deleteRentedGame(boardGame.getId());
        boardGameDbService.deleteGame(boardGame.getTitle());
        userDbService.deleteUser(user.getId());
    }
}
