package ru.est0y.diceChess.services.rules.movementFilters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.services.directions.DirectionChecker;
import ru.est0y.diceChess.services.directions.EmptySquareDirection;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueenMovementFilter implements PieceMovementFilter<Queen> {
    private final DirectionChecker directionChecker;

    private final EmptySquareDirection emptySquareDirection;

    @Override
    public void doFilter(Queen queen, Move move, Board board) {
        log.info("QUEEN MOVE CHECK");
        var direction = directionChecker.check(move);
        log.info(direction.name());
        var attackedPiece = board.getPiece(move.getTo());
        if (attackedPiece.isPresent() && queen.getTeam() == attackedPiece.get().getTeam()) {
            throw new IllegalMoveException();
        }
        if (!((direction.isDiagonal() || direction.isOrthogonal())
                && emptySquareDirection.isPathClear(move, direction, board))) {
            throw new IllegalMoveException();
        }
    }
}
