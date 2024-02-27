package ru.est0y.diceChess.domain.dice;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Square;

@RequiredArgsConstructor
@ToString
public class PieceLocation {
    private final Square square;

    private final AbstractPiece abstractPiece;

    public Class<? extends AbstractPiece> getPieceClass() {
        return abstractPiece.getClass();
    }

    public Square getSquare() {
        return square;
    }

    public AbstractPiece getPiece() {
        return abstractPiece;
    }

}
