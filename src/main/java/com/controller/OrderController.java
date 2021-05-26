package com.controller;

import com.controller.exceptions.NoCopiesAvailableException;
import com.controller.exceptions.OrderNotFoundException;
import com.domain.Order;
import com.domain.dto.OrderDto;
import com.mapper.OrderMapper;
import com.service.OrderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("V1/order")
public class OrderController {
    @Autowired
    OrderMapper mapper;

    @Autowired
    OrderDbService dbService;

    @GetMapping(value = "getAllOrders")
    public List<OrderDto> getAllOrders() {
        return mapper.mapToOrderDtoList(dbService.getAllOrders());
    }

    @GetMapping(value = "getOrder")
    public OrderDto getRentedGame(@RequestParam Long orderId) throws OrderNotFoundException {
        return mapper.mapToOrderDto(dbService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) throws NoCopiesAvailableException {
        Order savedOrder = dbService.saveOrder(mapper.mapToOrder(orderDto));
        return mapper.mapToOrderDto(savedOrder);
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) throws NoCopiesAvailableException {
        dbService.saveOrder(mapper.mapToOrder(orderDto));
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteUser(@RequestParam Long orderId) throws OrderNotFoundException{
        try {
            dbService.deleteOrder(orderId);
        } catch (IllegalArgumentException e) {
            throw new OrderNotFoundException();
        }
    }
}
