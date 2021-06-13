package com.service.facade;

import com.domain.BoardGame;
import com.domain.Order;
import com.domain.Rent;
import com.domain.User;
import com.domain.dto.BoardGameDto;
import com.domain.dto.OrderDto;
import com.domain.dto.RentDto;
import com.domain.dto.UserDto;
import com.exceptions.*;
import com.mapper.BoardGameMapper;
import com.mapper.OrderMapper;
import com.mapper.RentedGamesMapper;
import com.mapper.UserMapper;
import com.service.BoardGameDbService;
import com.service.OrderDbService;
import com.service.RentedGameDbService;
import com.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
public class DatabasesFacade {
    @Autowired
    BoardGameDbService boardGameService;

    @Autowired
    BoardGameMapper boardGameMapper;

    @Autowired
    OrderDbService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RentedGameDbService rentedGameService;

    @Autowired
    RentedGamesMapper rentedGamesMapper;

    @Autowired
    UserDbService userService;

    @Autowired
    UserMapper userMapper;

    public List<BoardGameDto> getAllBoardGames() {
        return boardGameMapper.mapToGameDtoList(boardGameService.getAllGames());
    }

    public BoardGameDto getBoardGame(final String title) throws GameNotFoundException {
        return boardGameMapper.mapToGameDto(boardGameService.getGame(title).orElseThrow(GameNotFoundException::new));
    }

    public BoardGameDto getBoardGame(final Long gameId) throws GameNotFoundException {
        return boardGameMapper.mapToGameDto(boardGameService.getGame(gameId).orElseThrow(GameNotFoundException::new));
    }

    public void createGame(final BoardGameDto boardGameDto) {
        boardGameService.saveGame(boardGameMapper.mapToGame(boardGameDto));
    }

    public void deleteGame(final Long gameId) {
        boardGameService.deleteGame(gameId);
    }

    public void deleteGame(final String title) {
        boardGameService.deleteGame(title);
    }

    public BoardGameDto updateGame(final BoardGameDto boardGameDto) {
        BoardGame savedGame = boardGameService.saveGame(boardGameMapper.mapToGame(boardGameDto));
        return boardGameMapper.mapToGameDto(savedGame);
    }

    public List<OrderDto> getAllOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    public OrderDto getOrder(Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    public OrderDto updateOrder(OrderDto orderDto) throws NoCopiesAvailableException {
        Order savedOrder = orderService.saveOrder(orderMapper.mapToOrder(orderDto));
        return orderMapper.mapToOrderDto(savedOrder);
    }

    public void createOrder(OrderDto orderDto) throws NoCopiesAvailableException {
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    public void deleteOrder(Long orderId) throws OrderNotFoundException{
        try {
            orderService.deleteOrder(orderId);
        } catch (IllegalArgumentException e) {
            throw new OrderNotFoundException();
        }
    }

    public List<RentDto> getAllRentedGames() {
        return rentedGamesMapper.mapToRentedGameDtoList(rentedGameService.getAllRentedGames());
    }

    public RentDto getRentedGame(Long gameId) throws RentNotFoundException {
        return rentedGamesMapper.mapToRentedGameDto(rentedGameService.getRentedGame(gameId).orElseThrow(RentNotFoundException::new));
    }

    public RentDto updateRentedGame(RentDto rentDto) throws NoCopiesAvailableException {
        Rent savedGame = rentedGameService.saveRentedGame(rentedGamesMapper.mapToRentedGame(rentDto));
        return rentedGamesMapper.mapToRentedGameDto(savedGame);
    }

    public void createRentedGame(RentDto rentDto) throws NoCopiesAvailableException {
        try {
            rentedGameService.saveRentedGame(rentedGamesMapper.mapToRentedGame(rentDto));
        } catch (NoCopiesAvailableException e) {
            throw e;
        }
    }

    public void deleteRentedGame(Long gameId) throws RentNotFoundException{
        try {
            rentedGameService.deleteRentedGame(gameId);
        } catch (IllegalArgumentException e) {
            throw new RentNotFoundException();
        }
    }

    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsers());
    }

    public UserDto getUser(Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    public UserDto updateUser(UserDto userDto) {
        User savedUser = userService.saveUser(userMapper.mapToUser(userDto));
        return userMapper.mapToUserDto(savedUser);
    }

    public void deleteUser(Long userId) throws UserNotFoundException{
        try {
            userService.deleteUser(userId);
        } catch (IllegalArgumentException e) {
            throw new UserNotFoundException();
        }
    }

    public void registerUser(UserDto userDto) throws UserAlreadyExistException {
        try {
            userService.saveUser(userMapper.mapToUser(userDto));
        } catch (Exception e) {
            System.out.println(e);
            throw new UserAlreadyExistException();
        }
    }

    public void loginUser(UserDto userDto) {
        User user = userService.getUser(userDto.getUsername());
        user.setLogged(true);
        userService.saveUser(user);
    }

    public void logoutUser(UserDto userDto) {
        User user = userService.getUser(userDto.getUsername());
        user.setLogged(false);
        userService.saveUser(user);
    }
}
