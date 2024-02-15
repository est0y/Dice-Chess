package ru.est0y.diceChess.services.squares;

import ru.est0y.diceChess.domain.Square;

public interface SquareCache {
    Square getSquare(int x, int y);
}
