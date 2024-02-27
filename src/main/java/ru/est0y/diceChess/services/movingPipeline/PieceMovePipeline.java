package ru.est0y.diceChess.services.movingPipeline;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;

public interface PieceMovePipeline<T extends AbstractPiece> {
    void run(T piece, Move move, Game game3);
}
