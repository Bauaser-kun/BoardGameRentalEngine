package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"Backend", "Dto", "Frontend"})
public class GameBoardRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameBoardRentalApplication.class, args);
    }
}
