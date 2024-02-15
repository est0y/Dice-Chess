package ru.est0y.diceChess.services.rules.movementFilters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.services.directions.DirectionChecker;
import ru.est0y.diceChess.services.directions.EmptySquareDirection;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;

import static java.lang.Math.abs;

@Service
@Slf4j
@RequiredArgsConstructor
public class PawnMovementFilter implements PieceMovementFilter<Pawn> {
    private final DirectionChecker directionChecker;

    private final EmptySquareDirection emptySquareDirection;

    private final PieceRulesUtils pieceRulesUtils;

    @Override
    public void doFilter(Pawn pawn, Move move, Board board) {
        var direction = directionChecker.check(move);
        if (direction.isOrthogonal() && !pieceRulesUtils.isSquareEmpty(move.getTo(), board)) {
            throw new IllegalMoveException();
        }
        if (!pieceRulesUtils.isNotFriendlyFire(move, board)) {
            throw new IllegalMoveException();
        }
        var from = move.getFrom();
        var to = move.getTo();
        int diffY = abs(to.getY() - from.getY());
        if (direction.isOrthogonal()) {
            checkDirectionCorrectness(pawn, direction);
            checkDiffCorrectness(diffY, pawn, move, direction, board);
        } else if (direction.isDiagonal()) {
            checkSquareToMove(move.getTo(), board, diffY);
            checkVerticalMoveCorrect(pawn, direction);
        } else {
            throw new IllegalMoveException();
        }
    }

    private void checkDirectionCorrectness(Pawn pawn, Direction direction) {
        var isCorrectDirection = (direction == Direction.UP && pawn.getTeam() == 1) ||
                (direction == Direction.DOWN && pawn.getTeam() == 2);
        if (!isCorrectDirection) {
            throw new IllegalMoveException();
        }
    }

    private void checkDiffCorrectness(int diffY, Pawn pawn, Move move,
                                      Direction direction,
                                      Board board) {
        if (diffY == 1) {
            return;
        }
        if (diffY == 2 && !pawn.isMoved()) {
            if (!emptySquareDirection.isPathClear(move, direction, board)) {
                throw new IllegalMoveException();
            }
        } else {
            throw new IllegalMoveException();
        }
    }

    private void checkSquareToMove(Square to, Board board, int diffY) {
        if (board.getPiece(to).isEmpty() || diffY != 1) {
            throw new IllegalMoveException();
        }
    }

    private void checkVerticalMoveCorrect(Pawn pawn, Direction direction) {
        var isWhiteCorrectMove = pawn.getTeam() == 1 &&
                (direction == Direction.LEFT_UP || direction == Direction.RIGHT_UP);
        var isBlackCorrectMove = pawn.getTeam() == 2 &&
                (direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN);
        if (!(isWhiteCorrectMove || isBlackCorrectMove)) {
            throw new IllegalMoveException();
        }
    }
}
