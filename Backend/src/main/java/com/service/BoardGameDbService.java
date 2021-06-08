package com.service;

import com.database.BoardGameRepository;
import com.domain.BoardGame;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardGameDbService {
    @Autowired
    private final BoardGameRepository repository;

    public List<BoardGame> getAllGames() {
        return repository.findAll();
    }

    private Long getGameId(final String title) {
        return repository.findAll().stream().
                filter(boardGame -> boardGame
                        .getTitle()
                        .equals(title))
                .findFirst()
                .get()
                .getId();
    }

    public Optional<BoardGame> getGame(final String title){
        return repository.findById(getGameId(title));
    }

    public Optional<BoardGame> getGame(final Long gameId){
        return repository.findById(gameId);
    }

    public List<BoardGame> getGamesWithMechanic(final String mechanic) {
        return repository.findAll()
                .stream()
                .filter(game -> game.getType().toString().toLowerCase().contains(mechanic.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<BoardGame> getGamesWithTitle(final String title) {
        return repository.findAll()
                .stream()
                .filter(game -> game.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public BoardGame saveGame(BoardGame boardGame) {
        return repository.save(boardGame);
    }

    public void deleteGame(final Long gameId) {
        repository.deleteById(gameId);
    }

    public void deleteGame(final String title) {
        repository.deleteById(getGameId(title));
    }
}
