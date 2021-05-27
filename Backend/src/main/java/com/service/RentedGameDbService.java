package com.service;

import com.database.RentedGameRepository;
import com.domain.BoardGame;
import com.domain.RentedGame;
import com.exceptions.NoCopiesAvailableException;
import com.service.processor.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentedGameDbService {
    @Autowired
    private RentedGameRepository repository;

    @Autowired
    private BoardGameDbService boardGameDbService;

    @Autowired
    private Processor processor;

    public List<RentedGame> getAllRentedGames(){
        return repository.findAll();
    }

    public Optional<RentedGame> getRentedGame(final Long gameId) {
        return repository.findById(gameId);
    }

    public RentedGame saveRentedGame(RentedGame rentedGame) throws NoCopiesAvailableException {
        BoardGame gameToRent = boardGameDbService.getGame(rentedGame.getGame().getTitle()).get();
        if (gameToRent.getCopies() > 0) {
            boardGameDbService.saveGame(processor.decreaseAvailableCopies(gameToRent));
            return repository.save(rentedGame);
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
