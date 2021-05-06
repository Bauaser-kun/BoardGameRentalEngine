package com.repository;

import com.domain.BoardGame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardGameRepositoryTestSuite {
    @Autowired
    BoardGameRepository boardGameRepository;

    @Autowired
    RentedGameRepository rentedGameRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldSaveGame(){
        //Given
        BoardGame game = new BoardGame("game test", 15.5, 5);

        //When
        boardGameRepository.save(game);

        //Then
        long id = game.getId();
        Optional<BoardGame> readGame = boardGameRepository.findById(id);
        assertTrue(readGame.isPresent());

        //Cleanup
        boardGameRepository.deleteById(id);
        }
}
