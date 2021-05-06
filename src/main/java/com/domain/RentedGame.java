package com.domain;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToOne
    private BoardGame game;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
