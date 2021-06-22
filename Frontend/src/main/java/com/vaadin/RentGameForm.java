package com.vaadin;

import com.domain.BoardGame;
import com.domain.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Rent")
public class RentGameForm extends FormLayout {
    private MainView mainView;
    private ViewElements elements = new ViewElements();

    private TextField title = elements.createTextField("title");
    private TextField username = elements.createTextField("username");
    private Button rent = elements.createButton("rentGame");
    private DatePicker rentedOn = new DatePicker("Rented since");
    private DatePicker returnDate = new DatePicker("Rented till");

    private Binder<BoardGame> gameBinder = new Binder<>(BoardGame.class);
    private Binder<User> userBinder = new Binder<>(User.class);

    public RentGameForm (MainView mainView) {
        this.mainView = mainView;
        rent.addClickListener(event -> rentGame());

        gameBinder.forField(title).bind(BoardGame::getTitle, BoardGame::setTitle);
        userBinder.forField(username).bind(User::getUsername, User::setUsername);
        gameBinder.bindInstanceFields(this);
        userBinder.bindInstanceFields(this);

        add(title, username, rentedOn, returnDate, rent);
    }

    public void rentGame(){

    }
}
