package com.vaadin;

import com.controller.UserController;
import com.mapper.UserMapper;
import com.service.UserDbService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("login")
public class LoginView extends Composite<VerticalLayout> {
    @Autowired
    UserController controller;

    @Autowired
    UserMapper mapper;

    @Autowired
    UserDbService dbService;

    private ViewElements elements = new ViewElements();
    private LoginForm login = new LoginForm();

    VerticalLayout layout = elements.createCenteredVerticalLayout();
    Button register = elements.createButton("register");
    Button back = elements.createButton("Back");

    public LoginView() {
        getContent().add(layout);
        register.addClickListener(event -> UI.getCurrent().navigate(RegisterView.class));
        login.addLoginListener(event -> loginUser(event.getUsername(), event.getPassword()));

        layout.add(
                login, register, back
        );
    }

    private void loginUser(String username, String password) {
        if (verifyPassword(password, username))
        controller.loginUser(mapper.mapToUserDto(dbService.getUser(username)));
    }

    private boolean verifyPassword(String password, String username) {
        return dbService.getUser(username).getPassword().equals(password);
    }

    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
    }
}
