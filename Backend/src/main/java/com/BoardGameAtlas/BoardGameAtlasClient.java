package com.BoardGameAtlas;

import com.BoardGameAtlas.config.AtlasConfig;
import com.domain.dto.AtlasForumPostDto;
import com.domain.dto.AtlasForumPostListDto;
import com.domain.dto.AtlasGameDto;
import com.domain.dto.AtlasGameListDTO;
import com.exceptions.GameNotFoundException;
import com.exceptions.NoRelatedTopicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardGameAtlasClient {
    private final RestTemplate restTemplate;
    private final AtlasConfig atlasConfig;

    public List<AtlasGameDto> getKickstarterGamesFromBoardGameAtlas() {
                URI url = UriComponentsBuilder.fromHttpUrl(atlasConfig.getApiEndpoint() + "/search")
                .queryParam("kickstarter", "true")
                .queryParam("client_id", atlasConfig.getClientId())
                .queryParam("fields", "name")
                .build()
                .encode()
                .toUri();

        try {
            AtlasGameListDTO responseList = restTemplate.getForObject(url, AtlasGameListDTO.class);
            return responseList.getGames().stream().collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    public List<AtlasGameDto> getAllGamesFromBoardGameAtlas() {
        URI url = UriComponentsBuilder.fromHttpUrl(atlasConfig.getApiEndpoint() + "/search")
                .queryParam("client_id", atlasConfig.getClientId())
                .build()
                .encode()
                .toUri();

        try {
            AtlasGameListDTO responseList = restTemplate.getForObject(url, AtlasGameListDTO.class);
            return responseList.getGames().stream().collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    public List<AtlasGameDto> getGame(String title) throws GameNotFoundException {
        URI url = UriComponentsBuilder.fromHttpUrl(atlasConfig.getApiEndpoint() + "/search")
                .queryParam("name", title)
                .queryParam("client_id", atlasConfig.getClientId())
                .build()
                .encode()
                .toUri();

        try {
            AtlasGameListDTO responseList = restTemplate.getForObject(url, AtlasGameListDTO.class);
            if (responseList.getGames().size() > 1)
                System.out.println("More than one game meets search criteria:");

            List<AtlasGameDto> returnList = responseList.getGames().stream()
                    .filter(game -> game.getName().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
            if (returnList.size() > 0) {
                return returnList;
            } else {
                throw new GameNotFoundException();
            }
        } catch (Exception e) {
            throw new GameNotFoundException();
        }
    }

    public List<AtlasForumPostDto> getAllForumTopics() {
        URI url = UriComponentsBuilder.fromHttpUrl(atlasConfig.getApiEndpoint() + "/forum")
                .queryParam("client_id", atlasConfig.getClientId())
                .build()
                .encode()
                .toUri();
        System.out.println(url);

        try {
            AtlasForumPostListDto response = restTemplate.getForObject(url, AtlasForumPostListDto.class);
            if (response.getPosts().size() > 1)
                System.out.println("More than one topic meets search criteria:");

            return response.getPosts();
        } catch (Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    public List<AtlasForumPostDto> getForumTopics(String topic) throws NoRelatedTopicException{
        URI url = UriComponentsBuilder.fromHttpUrl(atlasConfig.getApiEndpoint() + "/forum")
                .queryParam("client_id", atlasConfig.getClientId())
                .build()
                .encode()
                .toUri();
        System.out.println(url);

        try {
            AtlasForumPostListDto response = restTemplate.getForObject(url, AtlasForumPostListDto.class);
            if (response.getPosts().size() > 1)
                System.out.println("More than one topic meets search criteria:");

            List<AtlasForumPostDto> returnList = response.getPosts().stream()
                    .filter(game -> game.getTitle().toLowerCase().contains(topic.toLowerCase()))
                    .collect(Collectors.toList());
            if (returnList.size() > 0) {
                return returnList;
            } else {
                throw new NoRelatedTopicException();
            }
        } catch (Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }
}

