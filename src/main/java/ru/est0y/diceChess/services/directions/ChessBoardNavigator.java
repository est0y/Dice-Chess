package ru.est0y.diceChess.services.directions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.board.BoardSize;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.services.squares.SquareCache;

@RequiredArgsConstructor
@Service
public class ChessBoardNavigator implements BoardNavigator {
    private final SquareCache squareCache;

    @Override
    public Square nextSquare(Square square, Direction direction, BoardSize boardSize) {
        return switch (direction) {
            case UNKNOWN -> throw new IllegalArgumentException("Direction is UNKNOWN");
            case UP -> nextSquare(square, boardSize, 0, 1);
            case DOWN -> nextSquare(square, boardSize, 0, -1);
            case LEFT -> nextSquare(square, boardSize, -1, 0);
            case RIGHT -> nextSquare(square, boardSize, 1, 0);
            case LEFT_UP -> nextSquare(square, boardSize, -1, 1);
            case LEFT_DOWN -> nextSquare(square, boardSize, -1, -1);
            case RIGHT_UP -> nextSquare(square, boardSize, 1, 1);
            case RIGHT_DOWN -> nextSquare(square, boardSize, 1, -1);
        };
    }

    private Square nextSquare(Square square, BoardSize boardSize, int horizontalTerm, int verticalTerm) {
        var x = square.getX();
        var y = square.getY();
        var newX = x + horizontalTerm;
        var newY = y + verticalTerm;
        var maxX = boardSize.getX();
        var maxY = boardSize.getY();
        if (newX <= maxX && newY <= maxY && newX > 0 && newY > 0) {
            return squareCache.getSquare(newX, newY);
        } else {
            throw new NotExistSquareException(newX, newY);
        }
    }
}
