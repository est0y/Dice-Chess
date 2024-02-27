package ru.est0y.diceChess.domain.pieces;

import lombok.ToString;
import ru.est0y.diceChess.domain.AbstractPiece;

@ToString
public class Rook extends AbstractPiece {
    public Rook(int team) {
        super(team);
    }
}
