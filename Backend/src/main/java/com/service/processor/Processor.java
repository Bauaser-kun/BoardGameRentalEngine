package com.service.processor;

import com.domain.BoardGame;
import org.springframework.stereotype.Service;

@Service
public class Processor {
    public BoardGame decreaseAvailableCopies(BoardGame boardGame){
        return new BoardGame(boardGame.getId(), boardGame.getTitle(), boardGame.getType(), boardGame.getPrice(),
                boardGame.getCopies() - 1, boardGame.getRentedGames(), boardGame.getOrder());
    }

    public BoardGame increaseAvailableCopies(BoardGame boardGame) {
        return new BoardGame(boardGame.getId(), boardGame.getTitle(), boardGame.getType(), boardGame.getPrice(),
                boardGame.getCopies() + 1, boardGame.getRentedGames(), boardGame.getOrder());
    }
}
