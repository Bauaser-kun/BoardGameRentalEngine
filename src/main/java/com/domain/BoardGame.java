package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;


    public BoardGame(String title, Float price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
    }
}
