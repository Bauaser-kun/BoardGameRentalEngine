package com.vaadin;

import com.domain.BoardGame;
import com.service.BoardGameDbService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {
    private Grid<BoardGame> grid = new Grid<>(BoardGame.class);
    private ViewElements elements = new ViewElements();

    TextField typeFilter = elements.createFilterField("Find game by mechanics");
    TextField titleFilter = elements.createFilterField("Find game");
    Button atlasButton = elements.createMoveButton("Atlas");

    @Autowired
    private BoardGameDbService dbService;

    public MainView(BoardGameDbService dbService){
        this.dbService = dbService;

        grid.setColumns("id","copies", "price", "title", "type");
        typeFilter.addValueChangeListener(event -> updateGridWithMechanicSearch());
        titleFilter.addValueChangeListener(event -> updateGridWithTitleSearch());
        atlasButton.addClickListener(event -> UI.getCurrent().navigate(AtlasView.class));

        add(grid, typeFilter, titleFilter, atlasButton);
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
