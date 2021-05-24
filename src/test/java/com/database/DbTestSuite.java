package com.database;

import com.controller.exceptions.GameNotFoundException;
import com.controller.exceptions.NoCopiesAvailableException;
import com.domain.BoardGame;
import com.domain.Order;
import com.domain.RentedGame;
import com.domain.User;
import com.domain.dto.OrderDto;
import com.service.BoardGameDbService;
import com.service.OrderDbService;
import com.service.RentedGameDbService;
import com.service.UserDbService;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
                rentedGameDbService.saveRentedGame(new RentedGame(boardGameDbService.getGame(title).get())));

        //CleanUp
        boardGameDbService.deleteGame(game.getTitle());
    }

    @SneakyThrows
    @Test
    void shouldCreateOrder(){
        //Given
        User user = new User();
        BoardGame boardGame = new BoardGame("game test", 15.5, 5);
        Order order = new Order(user, List.of(boardGame));

        //When
        orderDbService.saveOrder(order);

        //Then
        Long id = order.getId();
        Optional<Order> readOrder = orderDbService.getOrder(id);
        assertTrue(readOrder.isPresent());
    }
}
