package ru.est0y.diceChess.services.rules.postProcessors;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.Move;

public interface PiecePostProcessor<T extends AbstractPiece> {
    void process(T piece, Move move, Board board);


    //Class<T> getType();

    // boolean isLegalMove(T piece, Move move, Board board);
}
