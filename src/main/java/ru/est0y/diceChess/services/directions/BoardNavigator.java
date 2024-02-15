package ru.est0y.diceChess.services.directions;

import ru.est0y.diceChess.domain.board.BoardSize;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;

public interface BoardNavigator {
    public Square nextSquare(Square square, Direction direction, BoardSize boardSize);
}
