package ru.est0y.diceChess.domain.pieces;

import ru.est0y.diceChess.domain.AbstractPiece;

public class King extends AbstractPiece {
    private boolean isMoved = false;

    public King(int team) {
        super(team);
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }
}
