package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.directions.EmptySquareDirection;
import ru.est0y.diceChess.services.directions.MoveDirectionManager;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PawnLegalMovement implements LegalMovement<Pawn> {


    private final PieceRulesUtils pieceRulesUtils;

    private final MoveDirectionManager moveDirectionManager;

    private final EmptySquareDirection emptySquareDirection;

    @Override
    public List<Square> getAllLegalMoves(Square square, Pawn piece, Board board) {
        var team = piece.getTeam();
        if (team == 1) {
            return getAllLegalMoves(square, piece, board,
                    Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP);
        } else if (team == 2) {
            return getAllLegalMoves(square, piece, board,
                    Direction.DOWN, Direction.LEFT_DOWN, Direction.RIGHT_DOWN);
        } else {
            throw new IllegalStateException();
        }
    }

    private List<Square> getAllLegalMoves(Square square, Pawn piece, Board board,
                                          Direction verticalDirection,
                                          Direction diagonalLeft,
                                          Direction diagonalRight) {
        List<Square> moves = new ArrayList<>(emptySquareDirection
                .getEmptyToOccupied(square, verticalDirection, board, 2));
        if (piece.isMoved() && (moves.size() == 2)) {
            moves.remove(1);
        }
        var verticalMoves = Stream.of(diagonalLeft, diagonalRight)
                .flatMap(d -> moveDirectionManager.moveToPossible(square, d, board, 1)
                        .stream())
                .filter(s -> board.getPiece(s).isPresent() && pieceRulesUtils
                        .isNotFriendlyFire(square, s, board)).toList();
        moves.addAll(verticalMoves);
        return moves;
    }


}

