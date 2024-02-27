package ru.est0y.diceChess.services.gameManagement;

import ru.est0y.diceChess.domain.AbstractPiece;

public interface PieceFactory<T extends AbstractPiece> {
    T create(int team);
}
