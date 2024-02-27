package ru.est0y.diceChess.services.piecesManagement;

import ru.est0y.diceChess.domain.AbstractPiece;

public interface PieceCreatorManager {
    AbstractPiece create(char lettrer, int team);
}
