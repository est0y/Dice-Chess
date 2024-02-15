package ru.est0y.diceChess.services.diceChess;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.services.IllegalMoveException;

@Service
public class LegalPiecesFilter implements MoveFilter {
    public void doFilter(Move move, Game game) {
        var square = move.getFrom();
        var pieceClass = game.getBoard().getPiece(square).orElseThrow().getClass();
        if (!game.getLegalPieces().contains(pieceClass)) {
            throw new IllegalMoveException();
        }
    }
}
