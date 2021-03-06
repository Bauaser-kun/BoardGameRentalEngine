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
public class Rent {
    @Id
    private Long id;

    @ManyToOne
    @MapsId
    private BoardGame game;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @Column(name = "Rented_on")
    private LocalDate rentedOn;

    @Column(name = "ReturnDate")
    private LocalDate returnDate;

    public Rent(BoardGame game) {
        this.game = game;
    }
}
