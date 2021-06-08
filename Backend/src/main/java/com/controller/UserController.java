package com.controller;

import com.domain.Status;
import com.domain.User;
import com.domain.dto.UserDto;
import com.exceptions.UserNotFoundException;
import com.mapper.UserMapper;
import com.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/V1/User")
public class UserController {
    @Autowired
    UserMapper mapper;

    @Autowired
    UserDbService dbService;

    @GetMapping(value = "getAll")
    public List<UserDto> getUsers() {
        return mapper.mapToUserDtoList(dbService.getUsers());
    }

    @GetMapping(value = "get")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return mapper.mapToUserDto(dbService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @PutMapping(value = "update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User savedUser = dbService.saveUser(mapper.mapToUser(userDto));
        return mapper.mapToUserDto(savedUser);
    }

    @PostMapping(value = "register")
    public Status registerUser(@RequestBody UserDto userDto) {
        List<UserDto> users = mapper.mapToUserDtoList(dbService.getUsers());

        for (UserDto user : users) {
            if (user.getUsername() == userDto.getUsername()) {
                return Status.USER_ALREADY_EXISTS;
            }
        }

        dbService.saveUser(mapper.mapToUser(userDto));
        return Status.SUCCESS;
    }

    @PostMapping(value = "login")
    public Status loginUser(@RequestBody UserDto userDto) {
        List<UserDto> users = mapper.mapToUserDtoList(dbService.getUsers());

        for (UserDto user : users) {
            if (user.getUsername() == userDto.getUsername()) {
                user.setLogged(true);
                dbService.saveUser(mapper.mapToUser(user));
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @PostMapping(value = "logout")
    public Status logoutUser(@RequestBody UserDto userDto) {
        List<UserDto> users = mapper.mapToUserDtoList(dbService.getUsers());

        for (UserDto user : users) {
            if (user.getUsername() == userDto.getUsername()) {
                user.setLogged(false);
                dbService.saveUser(mapper.mapToUser(user));
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @DeleteMapping(value = "delete")
    public Status deleteUser(@RequestParam Long userId) throws UserNotFoundException{
       try {
           dbService.deleteUser(userId);
           return Status.SUCCESS;
       } catch (IllegalArgumentException e) {
           throw new UserNotFoundException();
       }
    }
}
