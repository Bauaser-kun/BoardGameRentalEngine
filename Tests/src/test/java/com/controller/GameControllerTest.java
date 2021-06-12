package com.controller;

import com.domain.BoardGame;
import com.domain.MechanicType;
import com.domain.dto.BoardGameDto;
import com.domain.dto.OrderDto;
import com.exceptions.GameNotFoundException;
import com.google.gson.Gson;
import com.mapper.BoardGameMapper;
import com.service.BoardGameDbService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(GameController.class)
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardGameMapper mapper;

    @MockBean
    private GameController controller;

    @MockBean
    private BoardGameDbService service;

    @Test
    void shouldGetGames() throws Exception {
        //Given
        List<BoardGameDto> games = List.of(new BoardGameDto(1L, "Gloomhaven", MechanicType.COOP.toString(), 45, 2, new ArrayList<>(), new OrderDto()),
        new BoardGameDto(2L, "Gloomhaven edycja Polska", MechanicType.COOP.toString(), 45, 6, new ArrayList<>(), new OrderDto()),
        new BoardGameDto(3L, "ogródek", MechanicType.PUZZLE.toString(), 10.5, 10, new ArrayList<>(), new OrderDto()),
        new BoardGameDto(4L, "Ankh-Morpork", MechanicType.CARD.toString(), 15.75,5, new ArrayList<>(), new OrderDto()),
        new BoardGameDto(5L, "Munchkin", MechanicType.CARD.toString(), 5, 25, new ArrayList<>(), new OrderDto()),
        new BoardGameDto(6L, "Munchkin Gloomie", MechanicType.CARD.toString(), 6.50, 10, new ArrayList<>(), new OrderDto()),
        new BoardGameDto(7L, "Valhalla", MechanicType.DICE.toString(), 9.99, 6, new ArrayList<>(), new OrderDto()));

        when(controller.getGames()).thenReturn(games);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/Games/getGames")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(7)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].title", Matchers.is("Ankh-Morpork")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].type", Matchers.is("Card game")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[6].copies", Matchers.is(6)));

    }

    @Test
    void shouldGetGameById() throws Exception, GameNotFoundException {
        //Given
        BoardGameDto result = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        when(controller.getGame(anyLong(), isNull())).thenReturn(result);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/Games/getGame?gameId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("Puzzle Solving")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.copies", Matchers.is(10)));
    }

    @Test
    void shouldGetGameByTitle() throws Exception, GameNotFoundException {
        //Given
        BoardGameDto result = new BoardGameDto(1L, "ogródek", "Puzzle Solving", 10.5, 10, new ArrayList<>(), new OrderDto());
        when(controller.getGame(isNull(), anyString())).thenReturn(result);
        Gson gson = new Gson();
        String content = gson.toJson(result);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/Games/getGame?title=test")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("ogródek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is("Puzzle Solving")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.copies", Matchers.is(10)));
    }

    @Test
    void createGame() throws Exception {
        //Given
        BoardGameDto newGame = new BoardGameDto();
        BoardGame savedGame = new BoardGame();
        when(service.saveGame((any()))).thenReturn(savedGame);
        Gson gson = new Gson();
        String content = gson.toJson(savedGame);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/V1/Games/createGame")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateGame() throws Exception {
        //Given
        BoardGameDto savedGame = new BoardGameDto(1L, "Gloomhaven edycja Polska", "Cooperation", 45, 6, new ArrayList<>(), new OrderDto());
        when(controller.updateGame((any(BoardGameDto.class)))).thenReturn(savedGame);
        Gson gson = new Gson();
        String content = gson.toJson(savedGame);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/V1/Games/updateGame")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(savedGame.getPrice())));
    }
}