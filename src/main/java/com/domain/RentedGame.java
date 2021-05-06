package com.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity(name = "Rented")
public class RentedGame {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "Rented_on")
    private LocalDate rentedOn;

    @Column(name = "ReturnDate")
    private LocalDate returnDate;

    @Column(name = "game")
    private BoardGame game;
}
