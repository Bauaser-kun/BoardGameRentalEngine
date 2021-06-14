package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "User_ID")
    private long id;

    public User(String username, String password, String name, String surname, String userLevel, String email, LocalDate registeredOn, List<Order> orders, boolean isLogged) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userLevel = userLevel;
        this.email = email;
        this.registeredOn = registeredOn;
        this.orders = orders;
        this.isLogged = isLogged;
    }

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "userlevel", nullable = false)
    private String userLevel;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<Order> orders;

    @Column(name = "status", nullable = false)
    private boolean isLogged;

    public User(String username, String password, String name, String surname, String userLevel, String email, LocalDate registeredOn) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userLevel = userLevel;
        this.email = email;
        this.registeredOn = registeredOn;
    }
}
