package com.controller.BoardGameAtlas;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.domain.dto.AtlasForumTopicDto;
import com.domain.dto.AtlasGameDto;
import com.exceptions.NoRelatedTopicException;
import com.google.gson.Gson;
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

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(BoardGameAtlasController.class)
public class BoardGameAtlasControllerTestSuite {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardGameAtlasController controller;

    @MockBean
    BoardGameAtlasClient client;

    @Test
    void shouldFetchEmptyList() throws Exception {
        //Given
        when(client.getAllGamesFromBoardGameAtlas()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/V1/atlas/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchKickstarterGames() throws Exception {
        //Given
        List<AtlasGameDto> gameDtos = controller.getKickstarterGamesFromAtlas();

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/atlas/kickstarter")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(gameDtos.size())));

    }

    @Test
    void shouldFetchGame() throws Exception {
        //Given
        AtlasGameDto atlasGameDto = new AtlasGameDto("id", "name", "description", "testURL");
        Gson gson = new Gson();
        String content = gson.toJson(atlasGameDto);
        when(client.getGame(anyString())).thenReturn(Collections.singletonList(atlasGameDto));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/atlas/game?title=test")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("id")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("description")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].url", Matchers.is("testURL")));
    }

    @Test
    void shouldFetchAllForumTopics() throws Exception {
        //Given
        when(client.getAllForumTopics()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/atlas/forum")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchForumTopic() throws Exception, NoRelatedTopicException {
        //Given
        AtlasForumTopicDto topicDto = new AtlasForumTopicDto("test title", new URI("testUrl.com"));
        Gson gson = new Gson();
        String content = gson.toJson(topicDto);
        when(client.getForumTopics(anyString())).thenReturn(List.of(topicDto));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/atlas/topic?topic=test")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].post_url", Matchers.is("testUrl.com")));
    }
}
