package com.service;

import com.database.UserRepository;
import com.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDbService {
    @Autowired
    UserRepository repository;

    public List<User> getUsers(){
        return repository.findAll();
    }

    public Optional<User> getUser(final Long userId){
        return repository.findById(userId);
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }

    public void deleteUserByUsername(String username) {
        repository.deleteByUsername(username);
    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
