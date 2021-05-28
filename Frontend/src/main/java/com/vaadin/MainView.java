package com.vaadin;

import com.domain.BoardGame;
import com.service.BoardGameDbService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {
    private Grid<BoardGame> grid = new Grid<>(BoardGame.class);


    @Autowired
    private BoardGameDbService dbService;

    public MainView(BoardGameDbService dbService){
        this.dbService = dbService;

        grid.setColumns("id","copies", "price", "title", "type", "order");

        add(grid);
        updateGrid();
    }

    private void updateGrid() {
        grid.setItems(dbService.getAllGames());
    }
}
