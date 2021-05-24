package com.controller;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.controller.exceptions.GameNotFoundException;
import com.domain.dto.AtlasForumPostDto;
import com.domain.dto.AtlasGameDto;
import com.domain.dto.BoardGameDto;
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

    @GetMapping("getGame")
    public List<AtlasGameDto> getGame(@RequestParam String title) throws GameNotFoundException {
        List<AtlasGameDto> boardGames = boardGameAtlasClient.getGame(title);
        boardGames.forEach(boardGameDto -> System.out.println(boardGameDto.getName()));
        return boardGames;
    }

    @GetMapping("getBgaForum")
    public void getDiscusion(@RequestParam(required = false) String topic) {
        List<AtlasForumPostDto> posts = boardGameAtlasClient.getForumPosts(topic);
        posts.forEach(post -> System.out.println(post.getTitle() + "\n" + post.getPost_url()));
    }
}
