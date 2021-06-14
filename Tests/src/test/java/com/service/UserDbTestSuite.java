package com.service;

import com.domain.User;
import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDbTestSuite {
    @Autowired
    UserDbService service;

    @Autowired
    UserMapper mapper;

    @Test
    void shouldGetAllUsers() {
        //Given
        service.saveUser(new User("Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
        service.saveUser(new User("Momonga", "WhatShouldIDo", "Satoru", "Suzuki", "guild leader", "Momonga@GreatTombOfNazarick.com", LocalDate.of(2015,05,5) , new ArrayList<>(), false));
        service.saveUser(new User("Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
        service.saveUser(new User("Zaryus", "What?", "Zaryus", "Sasha", "lizardman tribes greatest warrior", "LizardmanWarrior@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));
        service.saveUser(new User("supervisor", "ILOVEAINZ", "Albedo", "Smaragdina", "guardians supervisor", "supervisor@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false));

        //When
        List<User> readUsers = service.getUsers();

        //Then
        assertEquals(readUsers.size(), 5);

        //Cleanup
        for (User user : readUsers) {
            service.deleteUserByUsername(user.getName());
        }
    }

    @Test
    void shouldGetUserById(){
        //Given
        User user = new User("Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false);
        service.saveUser(user);
        Long id = user.getId();

        //When
        User readUser = service.getUser(id).get();

        //Then
        assertEquals(readUser.getPassword(), "AINZSAMAFOREVER");
        assertEquals(readUser.getSurname(), "Bloodfallen");
        assertEquals(readUser.getRegisteredOn(), LocalDate.of(2015,05,5));
        assertEquals(readUser.isLogged(), false);

        //Cleanup
        service.deleteUserById(id);
    }

    @Test
    void shouldGetUserByUsername(){
        //Given
        User user = new User("Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), new ArrayList<>(), false);
        service.saveUser(user);
        String username = user.getUsername();

        //When
        User readUser = service.getUser(username);

        //Then
        assertEquals(readUser.getName(), "Shaltear");
        assertEquals(readUser.getUserLevel(), "guardian");
        assertEquals(readUser.getEmail(), "Floor1-3guardian@GreatTombOfNazarick.com");
        assertTrue(readUser.getOrders().isEmpty());

        //Cleanup
        service.deleteUserByUsername(username);
    }
}
