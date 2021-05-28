package com.vaadin;

import com.domain.BoardGame;
import com.service.BoardGameDbService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {
    private Grid<BoardGame> grid = new Grid<>(BoardGame.class);
    private TextField typeFilter = new TextField();

    @Autowired
    private BoardGameDbService dbService;

    public MainView(BoardGameDbService dbService){
        this.dbService = dbService;

        grid.setColumns("id","copies", "price", "title", "type", "order");
        typeFilter.setPlaceholder("Find game by Mechanics");
        typeFilter.setClearButtonVisible(true);
        typeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        typeFilter.addValueChangeListener(event -> updateGrid());

        add(grid, typeFilter);
        refreshGrid();
    }

    private void updateGrid() {
        grid.setItems(dbService.getGamesWithMechanic(typeFilter.getValue()));
    }

    private void refreshGrid() {
        grid.setItems(dbService.getAllGames());
    }
}
