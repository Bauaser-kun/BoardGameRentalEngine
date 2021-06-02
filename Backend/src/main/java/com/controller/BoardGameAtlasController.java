package com.controller;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.domain.dto.AtlasForumTopicDto;
import com.domain.dto.AtlasGameDto;
import com.exceptions.GameNotFoundException;
import com.exceptions.NoRelatedTopicException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/V1/atlas")
@RequiredArgsConstructor
public class BoardGameAtlasController {
    private final BoardGameAtlasClient boardGameAtlasClient;

    @GetMapping("kickstarter")
    public List<AtlasGameDto> getKickstarterGamesFromAtlas() {
        List<AtlasGameDto> boardGames = boardGameAtlasClient.getKickstarterGamesFromBoardGameAtlas();
        boardGames.forEach(boardGameDto -> System.out.println(boardGameDto.getName()));
        return boardGames;
    }

    @GetMapping("all")
    public List<AtlasGameDto> getAllGamesFromAtlas() {
        List<AtlasGameDto> boardGames = boardGameAtlasClient.getAllGamesFromBoardGameAtlas();
        boardGames.forEach(boardGameDto -> System.out.println(boardGameDto.getName()));
        return boardGames;
    }

    @GetMapping("Game")
    public List<AtlasGameDto> getGame(@RequestParam String title) throws GameNotFoundException {
        List<AtlasGameDto> boardGames = boardGameAtlasClient.getGame(title);
        boardGames.forEach(boardGameDto -> System.out.println(boardGameDto.getName()));
        return boardGames;
    }

    @GetMapping("Forum")
    public void getAllTopics() {
        List<AtlasForumTopicDto> posts = boardGameAtlasClient.getAllForumTopics();
        posts.forEach(post -> System.out.println(post.getTitle() + "\n" + post.getPost_url()));
    }

    @GetMapping("Topic")
    public void getTopics(@RequestParam String topic) throws NoRelatedTopicException {
        List<AtlasForumTopicDto> posts = boardGameAtlasClient.getForumTopics(topic);
        posts.forEach(post -> System.out.println(post.getTitle() + "\n" + post.getPost_url()));
    }
}
