package com.service;

import com.domain.BoardGame;
import com.domain.Order;
import com.domain.dto.UserDto;
import com.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDbTestSuite {
    @Autowired
    OrderDbService service;

    @Autowired
    OrderMapper mapper;

   // @SneakyThrows
    //@Test
    /*void shouldCreateOrder(){
        //Given
        UserDto user = new UserDto("username", "password", "name", "surname", "level", "email", LocalDate.of(2021, 01, 01));
        facade.registerUser(user);
        BoardGame boardGame = new BoardGame("game test", 15.5, 5);
        facade.createGame(boardGameMapper.mapToGameDto(boardGame));
        Order order = new Order(userMapper.mapToUser(user), List.of(boardGame));

        //When
        facade.createOrder(order);

        //Then
        Long id = order.getId();
        Optional<Order> readOrder = orderDbService.getOrder(id);
        assertTrue(readOrder.isPresent());

        //Cleanup
        orderDbService.deleteOrder(id);
        rentedGameDbService.deleteRentedGame(boardGame.getId());
        boardGameDbService.deleteGame(boardGame.getTitle());
        userDbService.deleteUser(user.getId());
    }*/
}
