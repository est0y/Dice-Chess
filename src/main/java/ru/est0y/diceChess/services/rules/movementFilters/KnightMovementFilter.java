package ru.est0y.diceChess.services.rules.movementFilters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class KnightMovementFilter implements PieceMovementFilter<Knight> {
    private final PieceRulesUtils pieceRulesUtils;

    @Override
    public void doFilter(Knight knight, Move move, Board board) {
        if (!pieceRulesUtils.isNotFriendlyFire(move, board)) {
            throw new IllegalMoveException();
        }
        var from = move.getFrom();
        var to = move.getTo();
        var diffX = Math.abs(from.getX() - to.getX());
        var diffY = Math.abs(from.getY() - to.getY());
        if (diffX + diffY != 3) {
            throw new IllegalMoveException();
        }
    }
}
