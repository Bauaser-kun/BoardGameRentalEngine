package com.service;

import com.domain.BoardGame;
import com.domain.Rent;
import com.exceptions.NoCopiesAvailableException;
import com.service.dbService.BoardGameDbService;
import com.service.dbService.RentedGameDbService;
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
    BoardGameDbService boardGameService;

    @Test
    void shouldThrowNoCopiesException() throws NoCopiesAvailableException {
        //Given & When
        BoardGame game = new BoardGame("game test", 15.5, 0);
        boardGameService.saveGame(game);
        Rent rent = new Rent(game);
        String title = game.getTitle();

        //Then
        assertThrows(NoCopiesAvailableException.class, ()->
                service.saveRentedGame(rent));

        //CleanUp
        boardGameService.deleteGame(game.getTitle());
    }
}
