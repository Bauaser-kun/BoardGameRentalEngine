package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<BoardGame> games;

    @Column(name = "order_created")
    private LocalDate createdOn;

    @Column(name = "game_rented_till")
    private LocalDate rentedTill;

    public Order(User user, List<BoardGame> games) {
        this.user = user;
        this.games = games;
    }

    public Order(User user, List<BoardGame> games, LocalDate createdOn, LocalDate rentedTill) {
        this.user = user;
        this.games = games;
        this.createdOn = createdOn;
        this.rentedTill = rentedTill;
    }
}
