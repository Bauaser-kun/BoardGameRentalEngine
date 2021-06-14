package com.service;

import com.domain.BoardGame;
import com.domain.dto.RentDto;
import com.exceptions.NoCopiesAvailableException;
import com.mapper.RentedGamesMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentDbTestSuite {
    @Autowired
    RentedGameDbService service;

    @Autowired
    RentedGamesMapper mapper;

    /*@Test
    void shouldThrowNoCopiesException() {
        //Given
        BoardGame game = new BoardGame("game test", 15.5, 0);
        String title = game.getTitle();

        //When
        facade.createGame(boardGameMapper.mapToGameDto(game));

        //Then
        assertThrows(NoCopiesAvailableException.class, ()->
                facade.createRentedGame(new RentDto(boardGameMapper.mapToGameDto(game))));

        //CleanUp
        facade.deleteGame(game.getTitle());
    }*/
}
