package com.vaadin;

import com.domain.BoardGame;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Rent")
public class RentGameView extends VerticalLayout {
    private Grid<BoardGame> grid = new Grid<>(BoardGame.class);

    @Autowired
    private ViewElements elements;

    public RentGameView () {
    }
}
