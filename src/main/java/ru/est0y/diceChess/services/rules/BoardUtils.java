package ru.est0y.diceChess.services.rules;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;

@Service
public class BoardUtils {
    public void makeMove(Move move, Board board) {
        var attackingPiece = board.getPiece(move.getFrom()).orElseThrow();
        board.putPiece(move.getTo(), attackingPiece);
        board.remove(move.getFrom());
    }

    public boolean isLastHorizontal(Square square, Board board) {
        var boardSize = board.getSize();
        var y = square.getY();
        return y == boardSize.getY() || y == 1;
    }

    public boolean isSquareExists(Square square, Board board) {
        var x = square.getX();
        var y = square.getY();
        var boardSize = board.getSize();
        return x >= 1 && y >= 1 && x <= boardSize.getX() && y <= boardSize.getY();
    }
}
