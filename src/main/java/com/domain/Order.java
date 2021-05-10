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
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<BoardGame> games;

    @Column(name = "order_created")
    private LocalDate createdOn;

    @Column(name = "game_rented_till")
    private LocalDate rentedTill;
}
