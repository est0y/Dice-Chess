package ru.est0y.diceChess.domain.board;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Square;

import java.util.Optional;

public interface Board extends Cloneable {
    Optional<AbstractPiece> getPiece(Square square);

    void putPiece(Square square, AbstractPiece abstractPiece);

    void remove(Square square);

    BoardSize getSize();

    Board clone();
}
