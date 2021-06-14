package com.service.dbService;

import com.database.RentedGameRepository;
import com.domain.BoardGame;
import com.domain.Rent;
import com.exceptions.NoCopiesAvailableException;
import com.service.processor.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentedGameDbService {
    @Autowired
    RentedGameRepository repository;

    @Autowired
    BoardGameDbService boardGameDbService;

    @Autowired
    Processor processor;

    public List<Rent> getAllRentedGames(){
        return repository.findAll();
    }

    public Optional<Rent> getRentedGame(final Long gameId) {
        return repository.findById(gameId);
    }

    public Rent saveRentedGame(Rent rent) throws NoCopiesAvailableException {
        BoardGame gameToRent = boardGameDbService.getGame(rent.getGame().getTitle()).get();
        if (gameToRent.getCopies() > 0) {
            boardGameDbService.saveGame(processor.decreaseAvailableCopies(gameToRent));
            return repository.save(rent);
        } else {
            throw new NoCopiesAvailableException();
        }
    }

    public void deleteRentedGame(final Long gameId) {
        BoardGame returnedGame = boardGameDbService.getGame(gameId).get();
        boardGameDbService.saveGame(processor.increaseAvailableCopies(returnedGame));
        repository.deleteById(gameId);
    }
}
