package com.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardGameRepositoryTestSuite {
    @Autowired
    BoardGameRepository repository;

    @Test
    void shouldSaveGame(){}
}
