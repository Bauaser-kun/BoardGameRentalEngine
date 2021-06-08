package com.controller;

import com.domain.User;
import com.domain.dto.UserDto;
import com.exceptions.UserAlreadyExistException;
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
    public void registerUser(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        try {
            dbService.saveUser(mapper.mapToUser(userDto));
        } catch (Exception e) {
            System.out.println(e);
            throw new UserAlreadyExistException();
        }
    }

    @PostMapping(value = "login")
    public void loginUser(@RequestBody UserDto userDto) {
        User user = dbService.getUser(userDto.getUsername());
        user.setLogged(true);
    }

    @PostMapping(value = "logout")
    public void logoutUser(@RequestBody UserDto userDto) {
        User user = dbService.getUser(userDto.getUsername());
        user.setLogged(false);
    }

    @DeleteMapping(value = "delete")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException{
       try {
           dbService.deleteUser(userId);
       } catch (IllegalArgumentException e) {
           throw new UserNotFoundException();
       }
    }
}
