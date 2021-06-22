package com.vaadin;

import com.domain.BoardGame;
import com.service.dbService.BoardGameDbService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class MainView extends VerticalLayout {
    private Grid<BoardGame> grid = new Grid<>(BoardGame.class);
    private RentGameForm rentGameForm = new RentGameForm(this);
    private ViewElements elements = new ViewElements();

    TextField typeFilter = elements.createFilterField("Find game by mechanics");
    TextField titleFilter = elements.createFilterField("Find game");
    Button atlasButton = elements.createButton("Atlas");
    Button loginButton = elements.createButton("Log in");

    @Autowired
    private BoardGameDbService dbService;

    public MainView(BoardGameDbService dbService){
        this.dbService = dbService;

        grid.setColumns("id", "title", "type", "copies", "price");

        typeFilter.addValueChangeListener(event -> updateGridWithMechanicSearch());
        titleFilter.addValueChangeListener(event -> updateGridWithTitleSearch());
        HorizontalLayout filters = new HorizontalLayout(titleFilter, typeFilter);
        HorizontalLayout rentForm = new HorizontalLayout(rentGameForm);

        atlasButton.addClickListener(event -> UI.getCurrent().navigate(AtlasView.class));
        loginButton.addClickListener(event -> UI.getCurrent().navigate(LoginView.class));

        add(loginButton, grid, rentForm, filters, atlasButton);
        refreshGrid();
    }

    private void updateGridWithMechanicSearch() {
        grid.setItems(dbService.getGamesWithMechanic(typeFilter.getValue()));
    }

    private void updateGridWithTitleSearch() {
        grid.setItems(dbService.getGamesWithTitle(titleFilter.getValue()));
    }

    private void refreshGrid() {
        grid.setItems(dbService.getAllGames());
    }
}
