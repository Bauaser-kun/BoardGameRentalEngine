package com.vaadin;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.domain.dto.AtlasGameDto;
import com.exceptions.GameNotFoundException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("Atlas")
public class AtlasView extends VerticalLayout {
    @Autowired
    BoardGameAtlasClient atlasClient;

    private ViewElements elements = new ViewElements();
    private Grid<AtlasGameDto> atlasGrid = new Grid<>(AtlasGameDto.class);

    Button kickstarter = elements.createButton("Kickstarter");
    Button kickstarterJSON = elements.createButton("Kickstarters as JSON");
    Button back = elements.createButton("Back to games");
    TextField searchField = elements.createSearchField("What do we look for?");
    Button JSON = elements.createButton("JSON");

    public AtlasView() {
        atlasGrid.setColumns("name", "url");
        kickstarter.addClickListener(event -> showKickstarters());
        kickstarterJSON.addClickListener(event -> getUI().get().getPage().open("V1/atlas/kickstarter"));
        JSON.addClickListener(event -> searchJSON());
        back.addClickListener(event -> UI.getCurrent().navigate(MainView.class));
        searchField.addValueChangeListener(event -> {
            try {
                searchGame();
            } catch (GameNotFoundException e) {
                searchField.clear();
            }
        });
        HorizontalLayout buttons = new HorizontalLayout(kickstarter, searchField, back);
        HorizontalLayout buttonsJSON = new HorizontalLayout(kickstarterJSON, JSON);

        add(buttons, buttonsJSON, atlasGrid);
    }

    private void searchJSON() {
        if (!searchField.isEmpty())
            getUI().get().getPage().open("V1/atlas/Game?title=" + searchField.getValue());
    }

    private void showKickstarters() {
        List<AtlasGameDto> response = atlasClient.getKickstarterGamesFromBoardGameAtlas();
        atlasGrid.setItems(response);
    }

    private void searchGame() throws GameNotFoundException {
        List<AtlasGameDto> response = atlasClient.getGame(searchField.getValue());
        atlasGrid.setItems(response);
    }


}
