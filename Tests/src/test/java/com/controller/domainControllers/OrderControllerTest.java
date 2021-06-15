package com.controller.domainControllers;

import com.domain.dto.BoardGameDto;
import com.domain.dto.OrderDto;
import com.domain.dto.UserDto;
import com.google.gson.Gson;
import com.service.facade.DatabasesFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DatabasesFacade facade;

    @Test
    void shouldGetAllOrders() throws Exception {
        //Given
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", LocalDate.of(2021, 01, 01), true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        BoardGameDto game2 = new BoardGameDto(2L, "ogródek", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        OrderDto order1 = new OrderDto(1l, user, List.of(game), LocalDate.now(), LocalDate.now().plusDays(5));
        OrderDto order2 = new OrderDto(2l, user, List.of(game), LocalDate.now(), LocalDate.now().plusDays(5));
        List<OrderDto> orders = List.of(order1, order2);
        when(facade.getAllOrders()).thenReturn(orders);

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
        when(facade.getOrder(anyLong())).thenReturn(order);

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
    void shouldUpdateOrder() throws Exception {
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        OrderDto order = new OrderDto(1l, user, List.of(game));
        OrderDto updatedOrder = new OrderDto(1l, user, List.of(game));
        when(facade.updateOrder((any(OrderDto.class)))).thenReturn(updatedOrder);
        Gson gson = new Gson();
        String content = gson.toJson(order);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/V1/order/updateOrder")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.games[0].title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.name", Matchers.is("name")));

    }

    @Test
    void shouldCreateOrder() throws Exception {
        //Given
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        OrderDto order = new OrderDto(1l, user, List.of(game));
        doNothing().when(facade).createOrder(order);
        Gson gson = new Gson();
        String content = gson.toJson(order);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/V1/order/createOrder")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        //Given
        doNothing().when(facade).deleteOrder(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/V1/order/deleteOrder?orderId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}