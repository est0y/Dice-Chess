package ru.est0y.services.directions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.BoardSize;
import ru.est0y.diceChess.services.directions.ChessBoardNavigator;
import ru.est0y.diceChess.services.squares.SquareCache;

class DirectionTest {

    @Test
    void nextSquare() {
        var spy= Mockito.spy(SquareCache.class);
        var direction = new ChessBoardNavigator(spy);
         direction.nextSquare(
                new Square(4, 5),
                Direction.UP,
                new BoardSize(8, 8)
        );
        Mockito.verify(spy).getSquare(5,6);
    }
}