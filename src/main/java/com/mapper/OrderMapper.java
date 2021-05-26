package com.mapper;

import com.domain.Order;
import com.domain.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public Order mapToOrder(OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getUser(), orderDto.getGames(), orderDto.getCreatedOn(),
                orderDto.getRentedTill());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getUser(), order.getGames(), order.getCreatedOn(),
                order.getRentedTill());
    }

    public List<Order> mapToOrderList(final List<OrderDto> orderDtos) {
        return orderDtos.stream().map(this::mapToOrder).collect(Collectors.toList());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        return orders.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }
}
