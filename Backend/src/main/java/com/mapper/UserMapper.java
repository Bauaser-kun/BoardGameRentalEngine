package com.mapper;

import com.domain.User;
import com.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    @Autowired
    private RentedGamesMapper gamesMapper;

    @Autowired
    private OrderMapper orderMapper;

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getName(), userDto.getSurname(),
                userDto.getUserLevel(), userDto.getEmail(), userDto.getRegisteredOn(), gamesMapper.mapToRentedGameList(userDto.getRentedGames()),
                orderMapper.mapToOrderList(userDto.getOrders()), userDto.isLogged());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getSurname(),
                user.getUserLevel(), user.getEmail(), user.getRegisteredOn(), user.isLogged(), gamesMapper.mapToRentedGameDtoList(user.getRentedGames()),
                orderMapper.mapToOrderDtoList(user.getOrders()));
    }

    public List<User> mapToUserList(final List<UserDto> userDtos) {
        return userDtos.stream().map(this::mapToUser).collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}
