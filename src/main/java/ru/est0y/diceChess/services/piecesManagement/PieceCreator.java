package ru.est0y.diceChess.services.piecesManagement;


import ru.est0y.diceChess.domain.AbstractPiece;

public interface PieceCreator<T> {
    AbstractPiece create(int team);

}
