package com.service;

import com.database.OrderRepository;
import com.domain.BoardGame;
import com.domain.Order;
import com.domain.RentedGame;
import com.exceptions.NoCopiesAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDbService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    RentedGameDbService dbService;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrder(final Long orderId) {
        return repository.findById(orderId);
    }

    public Order saveOrder(Order order) throws NoCopiesAvailableException {
        for (BoardGame game: order.getGames()) {
                dbService.saveRentedGame(new RentedGame(game.getId(), game, order.getUser(),
                        LocalDate.now(), LocalDate.now().plusDays(5)));
        }
        return repository.save(order);
    }

    public void deleteOrder(final Long orderId) {
        repository.deleteById(orderId);
    }
}