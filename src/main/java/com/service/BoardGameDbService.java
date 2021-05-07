package com.service;

import com.domain.BoardGame;
import com.repository.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public BoardGame saveGame(BoardGame boardGame) {
        return repository.save(boardGame);
    }

    public void deleteGame(final Long gameId) {
        repository.deleteById(gameId);
    }
}
