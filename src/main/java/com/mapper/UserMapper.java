package com.mapper;

import com.domain.User;
import com.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getName(), userDto.getSurname(),
                userDto.getUserLevel(), userDto.getEmail(), userDto.getRegisteredOn(), userDto.getRentedGames());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getSurname(),
                user.getUserLevel(), user.getEmail(), user.getRegisteredOn(), user.getRentedGames());
    }

    public List<User> userList(final List<UserDto> userDtos) {
        return userDtos.stream().map(this::mapToUser).collect(Collectors.toList());
    }

    public List<UserDto> userDtos(final List<User> users) {
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}