package com.controller;

import com.domain.BoardGame;
import com.domain.Order;
import com.domain.User;
import com.domain.dto.BoardGameDto;
import com.domain.dto.OrderDto;
import com.domain.dto.RentDto;
import com.domain.dto.UserDto;
import com.exceptions.NoCopiesAvailableException;
import com.exceptions.OrderNotFoundException;
import com.mapper.BoardGameMapper;
import com.mapper.OrderMapper;
import com.service.BoardGameDbService;
import com.service.OrderDbService;
import com.service.RentedGameDbService;
import com.service.UserDbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderMapper mapper;

    @MockBean
    OrderController controller;

    @MockBean
    OrderDbService service;

    @Test
    void shouldGetAllOrders() throws Exception {
        //Given
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", LocalDate.of(2021, 01, 01), true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        BoardGameDto game2 = new BoardGameDto(2L, "ogródek", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        OrderDto order1 = new OrderDto(1l, user, List.of(game), LocalDate.now(), LocalDate.now().plusDays(5));
        OrderDto order2 = new OrderDto(2l, user, List.of(game), LocalDate.now(), LocalDate.now().plusDays(5));
        List<OrderDto> orders = List.of(order1, order2);
        when(controller.getAllOrders()).thenReturn(orders);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/order/getAllOrders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].games[0].title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].user.name", Matchers.is("name")));
    }

    @Test
    void shouldGetOrder() throws Exception {
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", LocalDate.of(2021, 01, 01), true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        OrderDto order = new OrderDto(1l, user, List.of(game), LocalDate.now(), LocalDate.now().plusDays(5));
        when(controller.getOrder(anyLong())).thenReturn(order);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/order/Order?orderId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.games[0].title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.name", Matchers.is("name")));
    }

    @Test
    void shouldUpdateOrder() {
    }

    @Test
    void shouldCreateOrder() {
    }

    @Test
    void deleteUser() {
    }
}