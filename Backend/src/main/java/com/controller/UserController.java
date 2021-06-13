package com.controller;

import com.domain.dto.UserDto;
import com.exceptions.UserAlreadyExistException;
import com.exceptions.UserNotFoundException;
import com.mapper.UserMapper;
import com.service.UserDbService;
import com.service.facade.DatabasesFacade;
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

    @Autowired
    DatabasesFacade facade;

    @GetMapping(value = "getAll")
    public List<UserDto> getUsers() {
        return facade.getUsers();
    }

    @GetMapping(value = "get")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return facade.getUser(userId);
    }

    @PutMapping(value = "update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return facade.updateUser(userDto);
    }

    @PostMapping(value = "register")
    public void registerUser(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        facade.registerUser(userDto);
    }

    @PostMapping(value = "login")
    public void loginUser(@RequestBody UserDto userDto) {
        facade.loginUser(userDto);
    }

    @PostMapping(value = "logout")
    public void logoutUser(@RequestBody UserDto userDto) {
        facade.logoutUser(userDto);
    }

    @DeleteMapping(value = "delete")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException{
      facade.deleteUser(userId);
    }
}
