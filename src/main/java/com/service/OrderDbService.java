package com.service;

import com.domain.BoardGame;
import com.domain.Order;
import com.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDbService {
    @Autowired
    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrder(final Long orderId) {
        return repository.findById(orderId);
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    public void deleteOrder(final Long orderId) {
        repository.deleteById(orderId);
    }
}