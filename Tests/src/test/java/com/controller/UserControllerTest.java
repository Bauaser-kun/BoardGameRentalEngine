package com.controller;

import com.domain.User;
import com.domain.dto.UserDto;
import com.google.gson.Gson;
import com.service.facade.DatabasesFacade;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    DatabasesFacade facade;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetUsers() throws Exception {
        //Given
        List<UserDto> users = List.of(new UserDto(),
                new UserDto(1L, "Vampire", "AINZSAMAFOREVER", "Shaltear", "Bloodfallen", "guardian", "Floor1-3guardian@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>()),
                new UserDto(1L, "Momonga", "WhatShouldIDo", "Satoru", "Suzuki", "guild leader", "Momonga@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>()),
                new UserDto(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>()),
                new UserDto(1L, "Zaryus", "What?", "Zaryus", "Sasha", "lizardman tribes greatest warrior", "LizardmanWarrior@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>()),
                new UserDto(1L, "supervisor", "ILOVEAINZ", "Albedo", "Smaragdina", "guardians supervisor", "supervisor@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>()));
        when(facade.getUsers()).thenReturn(users);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/User/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].username", Matchers.is("Ainz")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Satoru")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[5].orders", Matchers.is(Matchers.empty())));
    }

    @Test
    void shouldGetUser() throws Exception {
        //Given
        UserDto ainz = new UserDto(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>());
        when(facade.getUser(anyLong())).thenReturn(ainz);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/V1/User/get?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("Ainz")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Momonga")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orders", Matchers.is(Matchers.empty())));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UserDto ainz = new UserDto(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", true, new ArrayList<>());
        when(facade.updateUser(any(UserDto.class))).thenReturn(ainz);
        Gson gson = new Gson();
        String content = gson.toJson(ainz);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/V1/User/update")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(ainz.getEmail())));
    }

    @Test
    void shouldRegisterUser() throws Exception {
        UserDto ainz = new UserDto(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", LocalDate.of(2015,05,5), true, new ArrayList<>());
        User savedUser = new User();
        doNothing().when(facade).registerUser(ainz);
        Gson gson = new Gson();
        String content = gson.toJson(savedUser);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/V1/User/register")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldLoginUser() throws Exception {
        UserDto ainz = new UserDto(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", false, new ArrayList<>());
        doNothing().when(facade).loginUser(any(UserDto.class));
        Gson gson = new Gson();
        String content = gson.toJson(ainz);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/V1/User/login")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldLogoutUser() throws Exception {
        UserDto ainz = new UserDto(1L, "Ainz", "THISisEvenWorse", "Momonga", "Of Ainz Ool Gown", "Supreme being", "SupremeBeing@GreatTombOfNazarick.com", true, new ArrayList<>());
        doNothing().when(facade).logoutUser(any(UserDto.class));
        Gson gson = new Gson();
        String content = gson.toJson(ainz);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/V1/User/logout")
                        .content(content)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given
        doNothing().when(facade).deleteUser(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/V1/User/delete?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}