package ru.est0y.diceChess.services.piecesManagement;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.pieces.Bishop;

public class BishopCreator implements PieceCreator<Bishop> {
    @Override
    public AbstractPiece create(int team) {
        return new Bishop(team);
    }
}
