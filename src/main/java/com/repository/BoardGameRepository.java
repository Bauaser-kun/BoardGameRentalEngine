package com.repository;

import com.domain.BoardGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BoardGameRepository extends CrudRepository<BoardGame, Long> {
    @Override
    List<BoardGame> findAll();

    @Override
    BoardGame save(BoardGame boardGame);

    @Override
    Optional<BoardGame> findById(Long gameId);

    @Override
    void deleteById(Long gameID);
}
