package ru.est0y.diceChess.services.rules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.board.BoardImpl;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.BoardSize;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.rules.movementFilters.PawnMovementFilter;

import java.util.Map;

@SpringBootTest
class PawnRuleTest {
    @Autowired
    private PawnMovementFilter pawnRule;

    private final BoardSize boardSize=new BoardSize(8,8);

    @Test
    void isLegalMove() {
        var board = new BoardImpl(
                Map.of(new Square(5, 2), new Pawn(1),
                        new Square(4, 3), new Knight(2)),boardSize);
        BoardImpl finalBoard = board;
        Assertions.assertDoesNotThrow(() -> pawnRule.doFilter(new Pawn(1), move(5, 2, 4, 3), finalBoard));
        Assertions.assertThrows(Exception.class, () -> pawnRule.doFilter(new Pawn(1), move(5, 2, 6, 3), finalBoard));
        Assertions.assertDoesNotThrow(() ->pawnRule.doFilter(new Pawn(1), move(5, 2, 5, 3), finalBoard));
        Assertions.assertDoesNotThrow(() ->pawnRule.doFilter(new Pawn(1), move(5, 2, 5, 4), finalBoard));
        Assertions.assertThrows(Exception.class, () ->pawnRule.doFilter(new Pawn(1), move(5, 2, 5, 5), finalBoard));
        board = new BoardImpl(
                Map.of(new Square(5, 2), new Pawn(1),
                        new Square(5, 3), new Knight(2)),boardSize);
        BoardImpl finalBoard1 = board;
        Assertions.assertThrows(Exception.class, () ->pawnRule.doFilter(new Pawn(1), move(5, 2, 5, 3), finalBoard1));
        Assertions.assertThrows(Exception.class, () ->pawnRule.doFilter(new Pawn(1), move(5, 2, 5, 4), finalBoard1));
        board = new BoardImpl(
                Map.of(new Square(5, 2), new Pawn(1),
                        new Square(4, 3), new Knight(1)),boardSize);
        BoardImpl finalBoard2 = board;
        Assertions.assertThrows(Exception.class, () ->pawnRule.doFilter(new Pawn(1), move(5, 2, 4, 3), finalBoard2));
    }

    private Move move(int fromX, int fromY, int toX, int toY) {
        return new Move(new Square(fromX, fromY), new Square(toX, toY));
    }
}