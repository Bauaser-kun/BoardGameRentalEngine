package com.controller;

import com.domain.dto.OrderDto;
import com.exceptions.NoCopiesAvailableException;
import com.exceptions.OrderNotFoundException;
import com.service.facade.DatabasesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("V1/order/")
public class OrderController {
    @Autowired
    DatabasesFacade facade;

    @GetMapping(value = "getAllOrders")
    public List<OrderDto> getAllOrders() {
        return facade.getAllOrders();
    }

    @GetMapping(value = "Order")
    public OrderDto getOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return facade.getOrder(orderId);
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) throws NoCopiesAvailableException {
        return facade.updateOrder(orderDto);
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) throws NoCopiesAvailableException {
        facade.createOrder(orderDto);
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) throws OrderNotFoundException{
        facade.deleteOrder(orderId);
    }
}
