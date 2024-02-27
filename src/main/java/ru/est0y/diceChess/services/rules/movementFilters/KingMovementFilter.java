package ru.est0y.diceChess.services.rules.movementFilters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.services.directions.DirectionChecker;
import ru.est0y.diceChess.services.directions.EmptySquareDirection;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class KingMovementFilter implements PieceMovementFilter<King> {
    private final DirectionChecker directionChecker;

    private final EmptySquareDirection emptySquareDirection;

    private final PieceRulesUtils pieceRulesUtils;

    @Override
    public void doFilter(King piece, Move move, Board board) {
        if (!pieceRulesUtils.isNotFriendlyFire(move, board)) {
            throw new IllegalMoveException();
        }
        var from = move.getFrom();
        var to = move.getTo();
        var diffX = Math.abs(from.getX() - to.getX());
        var diffY = Math.abs(from.getY() - to.getY());
        if (!(diffY + diffX == 2 || diffY + diffX == 1)) {
            throw new IllegalMoveException();
        }
    }
}
