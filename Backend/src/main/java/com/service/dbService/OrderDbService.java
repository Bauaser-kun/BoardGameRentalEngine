package com.service.dbService;

import com.database.OrderRepository;
import com.domain.BoardGame;
import com.domain.Order;
import com.domain.Rent;
import com.exceptions.NoCopiesAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDbService {
    @Autowired
    OrderRepository repository;

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
                dbService.saveRentedGame(new Rent(game.getId(), game, order.getUser(),
                        LocalDate.now(), LocalDate.now().plusDays(5)));
        }
        return repository.save(order);
    }

    public void deleteOrder(final Long orderId) {
        repository.deleteById(orderId);
    }
}