package com;

import com.domain.BoardGame;
import com.domain.MechanicType;
import com.domain.User;
import com.service.BoardGameDbService;
import com.service.OrderDbService;
import com.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PopulateDatabaseWithSampleData {
    @Autowired
    BoardGameDbService boardGameDbService;

    @Autowired
    UserDbService userDbService;

    @Test
    void populateDatabaseWithSampleData() {
        boardGameDbService.saveGame(new BoardGame("Gloomhaven", MechanicType.COOP, 45, 2));
        boardGameDbService.saveGame(new BoardGame("Gloomhaven edycja Polska", MechanicType.COOP, 45, 6));
        boardGameDbService.saveGame(new BoardGame("ogródek", MechanicType.PUZZLE, 10.5, 10));
        boardGameDbService.saveGame(new BoardGame("Ankh-Morpork", MechanicType.CARD, 15.75,5));
        boardGameDbService.saveGame(new BoardGame("Munchkin", MechanicType.CARD, 5, 25));
        boardGameDbService.saveGame(new BoardGame("Munchkin Gloomie", MechanicType.CARD, 6.50, 10));
        boardGameDbService.saveGame(new BoardGame("Valhalla", MechanicType.DICE, 9.99, 6));

        userDbService.saveUser(new User(1L, "Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
        userDbService.saveUser(new User(1L, "Momonga", "WhatShouldIDo", "Satoru", "Suzuki", "guild leader", "Momonga@GreatTombOfNazarick.com", LocalDate.of(2015,05,5) , new ArrayList<>(), false));
        userDbService.saveUser(new User(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
        userDbService.saveUser(new User(1L, "Zaryus", "What?", "Zaryus", "Sasha", "lizardman tribes greatest warrior", "LizardmanWarrior@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
        userDbService.saveUser(new User(1L, "supervisor", "ILOVEAINZ", "Albedo", "Smaragdina", "guardians supervisor", "supervisor@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
    }
}
