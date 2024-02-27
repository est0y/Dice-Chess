package ru.est0y.diceChess.services.directions;

import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;

public interface MoveMaker {
    Board makeMove(Move move, Board board);

    Board makeMove(Square from, Square to, Board board);
}
