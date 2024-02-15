package ru.est0y.diceChess.services.piecesManagement;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.pieces.Knight;

public class KnightCreator implements PieceCreator<Knight> {
    @Override
    public AbstractPiece create(int team) {
        return new Knight(team);
    }
}
