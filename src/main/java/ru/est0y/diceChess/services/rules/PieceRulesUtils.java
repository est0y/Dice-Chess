package ru.est0y.diceChess.services.rules;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;

@Service
public class PieceRulesUtils {
    public boolean isNotFriendlyFire(Move move, Board board) {
        return isNotFriendlyFire(move.getFrom(), move.getTo(), board);
    }

    public boolean isNotFriendlyFire(Square from, Square to, Board board) {
        var attackingPiece = board.getPiece(from).orElseThrow();
        var optionalAttackedPiece = board.getPiece(to);
        return optionalAttackedPiece.isEmpty() || attackingPiece.getTeam() != optionalAttackedPiece.get().getTeam();
    }

    public boolean isSquareEmpty(Square square, Board board) {
        return board.getPiece(square).isEmpty();
    }
}
