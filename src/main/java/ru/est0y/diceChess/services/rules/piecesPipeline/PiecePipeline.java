package ru.est0y.diceChess.services.rules.piecesPipeline;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;

public interface PiecePipeline<T extends AbstractPiece> {
    void makeMove(T piece, Move move, Game game);
}
