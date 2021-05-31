package com.vaadin;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.controller.BoardGameAtlasController;
import com.domain.BoardGame;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Atlas")
public class AtlasView extends HorizontalLayout {
    @Autowired
    BoardGameAtlasController atlasController;

    @Autowired
    BoardGameAtlasClient atlasClient;

    private ViewElements elements = new ViewElements();
    private Grid<BoardGame> atlasGrid = new Grid<>(BoardGame.class);

    Button kickstarter = elements.createMoveButton("Kickstarter");
    Button back = elements.createMoveButton("Back to games");
    TextField searchfield = elements.createSearchField("What do we look for?");


    public AtlasView(){
        atlasGrid.setColumns("title");
        kickstarter.addClickListener(event -> getUI().get().getPage().open("V1/atlas/kickstarter"));
        back.addClickListener(event -> UI.getCurrent().navigate(MainView.class));
        searchfield.addValueChangeListener(event -> searchGame());

        add(kickstarter, back, searchfield);
    }

    private void searchGame() {
        if (!searchfield.isEmpty())
        getUI().get().getPage().open("V1/atlas/Game?title=" + searchfield.getValue());
    }
}
