package ru.est0y.diceChess.services.rules.movementFilters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.services.directions.DirectionChecker;
import ru.est0y.diceChess.services.directions.EmptySquareDirection;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class BishopMovementFilter implements PieceMovementFilter<Bishop> {
    private final DirectionChecker directionChecker;

    private final EmptySquareDirection emptySquareDirection;

    private final PieceRulesUtils pieceRulesUtils;

    @Override
    public void doFilter(Bishop bishop, Move move, Board board) {
        log.info("BISHOP MOVE CHECK");
        var direction = directionChecker.check(move);
        if (!(direction.isDiagonal()
                && pieceRulesUtils.isNotFriendlyFire(move, board)
                && emptySquareDirection.isPathClear(move, direction, board))) {
         throw new IllegalMoveException();
        }
    }
}
