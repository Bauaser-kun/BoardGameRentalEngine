package com.vaadin;

import com.controller.domainControllers.UserController;
import com.domain.dto.UserDto;
import com.exceptions.UserAlreadyExistException;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

@Route("register")
public class RegisterView extends Composite {
    @Autowired
    private UserController controller;

    private ViewElements elements = new ViewElements();

    Button back = elements.createButton("Back");

    @Override
    protected Component initContent() {
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        PasswordField repeatedPassword = new PasswordField("Confirm Password");
        TextField name = new TextField("Name");
        TextField surname = new TextField("Surname");
        TextField email = new TextField("E-mail");
        back.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        return new VerticalLayout(
                back,
                new H2("Register"),
                username,
                password,
                repeatedPassword,
                name,
                surname,
                email,
                new Button("send", event -> {
                        register(
                                username.getValue(),
                                password.getValue(),
                                repeatedPassword.getValue(),
                                name.getValue(),
                                surname.getValue(),
                                email.getValue()
                        );
                }),
                back
        );
    }

    private void register(String username, String password1, String password2,
                          String name, String surname, String email) {

        UserDto newUser = new UserDto(1L, username, password1, name, surname,
                "new player", email, LocalDate.now(),
                false, new ArrayList<>());

        if (username.isEmpty() || password1.isEmpty() || password2.isEmpty()
        || name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
            Notification.show("Please fill all required fields");
        } else {
            try {
            controller.registerUser(newUser);
                UI.getCurrent().navigate(MainView.class);
            } catch (UserAlreadyExistException e) {
                Notification.show("User with this username already exists");
            }
        }
    }
}
