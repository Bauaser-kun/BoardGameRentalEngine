package com.database;

import com.domain.RentedGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentedGameRepository extends CrudRepository<RentedGame, Long> {
    @Override
    List<RentedGame> findAll();

    @Override
    RentedGame save(RentedGame rentedGame);

    @Override
    Optional<RentedGame> findById(Long gameId);

    @Override
    void deleteById(Long gameId);
}
