package com.database;

import com.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    User save (User user);

    @Override
    Optional<User> findById(Long uerId);

    @Override
    void deleteById(Long userId);

    User findByUsername(String username);

    void deleteByUsername(String username);
}
