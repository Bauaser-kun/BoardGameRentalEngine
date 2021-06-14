package com.service;

import com.controller.RentedGameController;
import com.domain.BoardGame;
import com.domain.Order;
import com.domain.Rent;
import com.domain.User;
import com.exceptions.NoCopiesAvailableException;
import com.mapper.OrderMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDbTestSuite {
    @Autowired
    OrderDbService service;

    @Autowired
    OrderMapper mapper;

    @Autowired
    UserDbService userService;

    @Autowired
    BoardGameDbService boardGameService;

    @Autowired
    RentedGameDbService rentedGameDbService;

    @Test
    void shouldSaveOrder() throws NoCopiesAvailableException {
        //Given
        User user = new User("username", "password", "name", "surname", "level", "email", LocalDate.of(2021, 01, 01));
        userService.saveUser(user);
        BoardGame boardGame = new BoardGame("game test", 15.5, 5);
        boardGameService.saveGame(boardGame);
        Order order = new Order(user, List.of(boardGame));

        //When
        service.saveOrder(order);
        Long id = order.getId();
        Optional<Order> readOrder = service.getOrder(id);

        //Then
        assertTrue(readOrder.isPresent());

        //Cleanup
        service.deleteOrder(id);
        rentedGameDbService.deleteRentedGame(boardGame.getId());
        boardGameService.deleteGame(boardGame.getTitle());
        userService.deleteUser(user.getId());
    }

    @Test
    void shouldGetOrders() throws NoCopiesAvailableException {
        //Given
        User user = new User("Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5));
        User user2 = new User("Momonga", "WhatShouldIDo", "Satoru", "Suzuki", "guild leader", "Momonga@GreatTombOfNazarick.com", LocalDate.of(2015,05,5));
        userService.saveUser(user);
        userService.saveUser(user2);
        BoardGame boardGame = new BoardGame("game test", 15.5, 5);
        boardGameService.saveGame(boardGame);
        Order order = new Order(user, List.of(boardGame));
        Order order2 = new Order(user2, List.of(boardGame));
        service.saveOrder(order);
        service.saveOrder(order2);

        //When
        List<Order> orders = service.getAllOrders();

        //Then
        assertEquals(2, orders.size());

        //Cleanup
        service.deleteOrder(order.getId());
        service.deleteOrder(order2.getId());
        rentedGameDbService.deleteRentedGame(boardGame.getId());
        boardGameService.deleteGame(boardGame.getTitle());
        userService.deleteUser(user.getId());
        userService.deleteUser(user2.getId());
    }

    @Test
   void shouldGetOrder() throws NoCopiesAvailableException {
        //Given
        User user = new User("Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5));
        User user2 = new User("Momonga", "WhatShouldIDo", "Satoru", "Suzuki", "guild leader", "Momonga@GreatTombOfNazarick.com", LocalDate.of(2015,05,5));
        userService.saveUser(user);
        userService.saveUser(user2);
        BoardGame boardGame = new BoardGame("game test", 15.5, 5);
        boardGameService.saveGame(boardGame);
        Order order = new Order(user, List.of(boardGame), LocalDate.now(), LocalDate.now().plusDays(5));
        Order order2 = new Order(user2, List.of(boardGame), LocalDate.now(), LocalDate.now().plusDays(5));
        service.saveOrder(order);
        service.saveOrder(order2);
        Long id = order2.getId();
        Long id2 = order.getId();

        //When
        Order readOrder = service.getOrder(id).get();
        Order readOrder2 = service.getOrder(id2).get();

        //Then
        assertEquals(LocalDate.now(), readOrder.getCreatedOn());
        assertTrue(LocalDate.now().plusDays(5).equals(readOrder2.getRentedTill()));

        //Cleanup
        service.deleteOrder(order.getId());
        service.deleteOrder(order2.getId());
        rentedGameDbService.deleteRentedGame(boardGame.getId());
        boardGameService.deleteGame(boardGame.getTitle());
        userService.deleteUser(user.getId());
        userService.deleteUser(user2.getId());
    }
}
