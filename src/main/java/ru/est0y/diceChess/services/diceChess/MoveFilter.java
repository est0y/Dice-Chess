package ru.est0y.diceChess.services.diceChess;

import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;

public interface MoveFilter {
    void doFilter(Move move, Game game);
}
