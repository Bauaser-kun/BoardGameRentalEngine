package com.controller;

import com.domain.dto.BoardGameDto;
import com.domain.dto.OrderDto;
import com.domain.dto.RentDto;
import com.domain.dto.UserDto;
import com.exceptions.RentNotFoundException;
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
@WebMvcTest(RentedGameController.class)
class RentedGameControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DatabasesFacade facade;

    @Test
    void shouldGetAllRentedGames() throws Exception {
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(2L, "ogródek", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        BoardGameDto game2 = new BoardGameDto(2L, "ogródek2", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        RentDto rent = new RentDto(1L, game, user);
        RentDto rent2 = new RentDto(1L, game2, user);
        RentDto rent3 = new RentDto();
        List<RentDto> rents = List.of(rent, rent2, rent3);
        when(facade.getAllRentedGames()).thenReturn(rents);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/rents/getAllRents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].game.title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].user.name", Matchers.is("name")));
    }

    @Test
    void shouldGetRentedGame() throws Exception {
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(2L, "ogródek", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        RentDto rent = new RentDto(1L, game, user);
        when(facade.getRentedGame(anyLong())).thenReturn(rent);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/rents/getRent?gameId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.game.title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.name", Matchers.is("name")));
    }

    @Test
    void shouldUpdateRentedGame() throws Exception {
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(2L, "ogródek", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        RentDto rent = new RentDto(1L, game, user);
        RentDto updatedRent = new RentDto(2L, game, user, LocalDate.now(), LocalDate.now().plusDays(5));
        when(facade.updateRentedGame(any(RentDto.class))).thenReturn(updatedRent);
        Gson gson = new Gson();
        String content = gson.toJson(rent);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/V1/rents/updateRent")
                        .characterEncoding("UTF-8")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.game.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.name", Matchers.is("name")));
    }

    @Test
    void shouldCreateRentedGame() throws Exception {
        UserDto user = new UserDto(1L, "username1", "password", "name", "surname", "level", "email", true, new ArrayList<>());
        BoardGameDto game = new BoardGameDto(2L, "ogródek", "Puzzle Solving", 10.5, 8, new ArrayList<>(), new OrderDto());
        RentDto rent = new RentDto(1L, game, user);
        doNothing().when(facade).createRentedGame(rent);
        Gson gson = new Gson();
        String content = gson.toJson(rent);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/V1/rents/createRent")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteRentedGame() throws Exception {
        doNothing().when(facade).deleteRentedGame(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/V1/rents/deleteRent?gameId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}