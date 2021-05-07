package com.service;

import com.domain.RentedGame;
import com.repository.RentedGameRepository;
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

    public List<RentedGame> getAllRentedGames(){
        return repository.findAll();
    }

    public Optional<RentedGame> getRentedGame(final Long gameId) {
        return repository.findById(gameId);
    }

    public RentedGame saveRentedGame(RentedGame rentedGame) {
        return repository.save(rentedGame);
    }

    public void deleteRentedGame(final Long gameId) {
        repository.deleteById(gameId);
    }
}
