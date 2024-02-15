package ru.est0y.diceChess.domain.board;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.dice.PieceLocation;

import java.util.List;
import java.util.Map;

public interface DiceChessBoard extends Board {
    Map<Class<? extends AbstractPiece>, List<PieceLocation>> getPlayerPieces(int team);
}
