package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "Rented")
@NoArgsConstructor
@AllArgsConstructor
public class RentedGame {
    @Id
    private Long id;

    @ManyToOne
    @MapsId
    private BoardGame game;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "Rented_on")
    private LocalDate rentedOn;

    @Column(name = "ReturnDate")
    private LocalDate returnDate;

    public RentedGame(BoardGame game) {
        this.game = game;
    }
}
