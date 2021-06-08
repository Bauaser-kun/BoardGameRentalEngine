package com.database;

import com.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentedGameRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();

    @Override
    Rent save(Rent rent);

    @Override
    Optional<Rent> findById(Long gameId);

    @Override
    void deleteById(Long gameId);
}
