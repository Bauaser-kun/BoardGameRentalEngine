package com.controller;

import com.controller.exceptions.UserNotFoundException;
import com.domain.User;
import com.domain.dto.UserDto;
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

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getUsers() {
        return mapper.mapToUserDtoList(dbService.getUsers());
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return mapper.mapToUserDto(dbService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User savedUser = dbService.saveUser(mapper.mapToUser(userDto));
        return mapper.mapToUserDto(savedUser);
    }

    @PostMapping(value = "createUser")
    public void createUser(@RequestBody UserDto userDto) {
        dbService.saveUser(mapper.mapToUser(userDto));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException{
       try {
           dbService.deleteUser(userId);
       } catch (IllegalArgumentException e) {
           throw new UserNotFoundException();
       }
    }
}
