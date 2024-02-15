package ru.est0y.services.directions;

import org.junit.jupiter.api.Test;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.BoardImpl;
import ru.est0y.diceChess.domain.board.BoardSize;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.services.directions.ChessBoardNavigator;
import ru.est0y.diceChess.services.directions.EmptySquareDirection;
import ru.est0y.diceChess.services.squares.SquareCacheImpl;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EmptySquareDirectionTest {

    @Test
    void moveUntilOccupied() {
        var em = new EmptySquareDirection(new ChessBoardNavigator(new SquareCacheImpl(new BoardSize(12, 12))));
        var map = Map.of(
                new Square(4, 4), new Bishop(1),
                new Square(7, 7), new Queen(2)
        );
        var board = new BoardImpl(map);

        System.out.println(em.getEmptyToOccupied(new Square(4, 4), Direction.RIGHT_UP, board));
        List<Square> result = em.getEmptyToOccupied(new Square(4, 4), Direction.RIGHT_UP, board);
        assertThat(result).containsOnly(new Square(5, 5), new Square(6, 6));
    }

    @Test
    void getEmptyToOccupied() {
        var em = new EmptySquareDirection(new ChessBoardNavigator(new SquareCacheImpl(new BoardSize(12, 12))));
        Map<Square, AbstractPiece> map = Map.of(
                new Square(4, 4), new Bishop(1)
        );
        var board = new BoardImpl(map);

        var result = em.getEmptyToOccupied(new Square(4, 4), Direction.RIGHT_UP, board, 1);
        assertThat(result).containsOnly(new Square(5,5));
    }
}