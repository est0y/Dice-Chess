package ru.est0y.diceChess.services.squares;

import org.springframework.stereotype.Component;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.BoardSize;

@Component
public class SquareCacheImpl implements SquareCache {
    private final Square[][] squares;

    public SquareCacheImpl(BoardSize boardSize) {
        int maxX = boardSize.getHorizontalSize();
        int maxY = boardSize.getVerticalSize();
        squares = new Square[maxX + 1][maxY + 1];
        for (int x = 1; x <= maxX; x++) {
            for (int y = 1; y <= maxY; y++) {
                squares[x][y] = new Square(x, y);
            }
        }
    }

    @Override
    public Square getSquare(int x, int y) {
        return squares[x][y];
    }
}
