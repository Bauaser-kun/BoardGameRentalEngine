package com.vaadin;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.domain.BoardGame;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Atlas")
public class AtlasView extends HorizontalLayout {
    @Autowired
    BoardGameAtlasClient atlasClient;

    private ViewElements elements = new ViewElements();
    private Grid<BoardGame> atlasGrid = new Grid<>(BoardGame.class);

    Button kickstarter = elements.createMoveButton("Kickstarter");

    public AtlasView(){
        atlasGrid.setColumns("title");
        kickstarter.addClickListener(event -> UI.getCurrent().navigate(/*atlasClient.getKickstarterGamesFromBoardGameAtlas().toString().*/));

        add(kickstarter);
    }
}
