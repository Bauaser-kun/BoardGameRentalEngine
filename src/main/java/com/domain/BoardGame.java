package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "GAMES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame {
    @Id
    @GeneratedValue
    @Column(name = "Game_ID")
    private long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Type")
    private String type;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Copies_On_Stock", nullable = false)
    private int copies;

    @OneToMany(mappedBy = "game")
    private List<RentedGame> rentedGames;

    public BoardGame(String title, double price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
    }
}
