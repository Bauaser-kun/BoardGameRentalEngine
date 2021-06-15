package com;

import com.domain.BoardGame;
import com.domain.MechanicType;
import com.domain.User;
import com.service.dbService.BoardGameDbService;
import com.service.dbService.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PopulateDatabaseWithSampleData {
    @Autowired
    BoardGameDbService boardGameDbService;

    @Autowired
    UserDbService userDbService;

   /*@Test
    void populateDatabaseWithSampleData() {
        boardGameDbService.saveGame(new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2));
        boardGameDbService.saveGame(new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6));
        boardGameDbService.saveGame(new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10));
        boardGameDbService.saveGame(new BoardGame("Ankh-Morpork", MechanicType.CARD, 15.75,5));
        boardGameDbService.saveGame(new BoardGame("Munchkin", MechanicType.CARD, 5, 25));
        boardGameDbService.saveGame(new BoardGame("Munchkin Gloomie", MechanicType.CARD, 6.50, 10));
        boardGameDbService.saveGame(new BoardGame("Valhalla", MechanicType.DICE, 9.99, 6));
    }*/
}
