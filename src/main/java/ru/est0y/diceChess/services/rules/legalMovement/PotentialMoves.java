package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.domain.pieces.Rook;
import ru.est0y.diceChess.services.directions.BoardNavigator;
import ru.est0y.diceChess.services.directions.NotExistSquareException;
import ru.est0y.diceChess.services.rules.BoardUtils;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.services.rules.offsets.OffsetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PotentialMoves {
    private final BoardNavigator boardNavigator;

    private final OffsetUtils offsetUtils;

    private final BoardUtils boardUtils;

    public List<Square> getPotentialMoves(Square square,
                                          Queen queen,
                                          Board board,
                                          List<Class<? extends AbstractPiece>> classes) {
        var directions = Arrays.stream(Direction.values()).filter(d -> d.isDiagonal() || d.isOrthogonal()).toList();
        return getPotentialMoves(square, queen, board, directions, classes);
    }

    public List<Square> getPotentialMoves(Square square,
                                          King piece,
                                          Board board,
                                          List<Class<? extends AbstractPiece>> classes) {
        var directions = Arrays.stream(Direction.values()).filter(d -> d.isDiagonal() || d.isOrthogonal()).toList();
        return getPotentialMoves(square, piece, board, directions, classes);
    }

    public List<Square> getPotentialMoves(Square square,
                                          Bishop piece,
                                          Board board,
                                          List<Class<? extends AbstractPiece>> classes) {
        var directions = Arrays.stream(Direction.values()).filter(Direction::isDiagonal).toList();
        return getPotentialMoves(square, piece, board, directions, classes);
    }

    public List<Square> getPotentialMoves(Square square,
                                          Rook piece,
                                          Board board,
                                          List<Class<? extends AbstractPiece>> classes) {
        var directions = Arrays.stream(Direction.values()).filter(Direction::isOrthogonal).toList();
        return getPotentialMoves(square, piece, board, directions, classes);
    }

    public List<Square> getPotentialMoves(Square square,
                                          Knight piece,
                                          Board board,
                                          List<Class<? extends AbstractPiece>> classes) {
        return offsetUtils.apply(square, KnightLegalMovement.OFFSETS)
                .stream()
                .filter(s -> boardUtils.isSquareExists(s, board))
                .filter(s -> board.getPiece(s).isPresent())
                .filter(s -> classes.stream().anyMatch(c -> c.equals(board.getPiece(s).orElseThrow().getClass())))
                .filter(s -> piece.getTeam() == board.getPiece(s).orElseThrow().getTeam())
                .toList();
    }

    public List<Square> getPotentialMoves(Square square,
                                          Pawn piece,
                                          Board board,
                                          List<Class<? extends AbstractPiece>> classes) {
        return getPotentialMoves(square, piece, board, List.of(Direction.UP), classes);
    }


    private List<Square> getPotentialMoves(Square square,
                                           AbstractPiece piece,
                                           Board board,
                                           List<Direction> directions,
                                           List<Class<? extends AbstractPiece>> classes
    ) {
        List<Square> result = new ArrayList<>();

        for (var direction : directions) {
            try {
                var nowSquare = boardNavigator.nextSquare(square, direction, board.getSize());
                var optionalPiece = board.getPiece(nowSquare);
                if (optionalPiece.isPresent() &&
                        classes.stream().anyMatch(c -> c.equals(optionalPiece.get().getClass()))
                        && optionalPiece.get().getTeam() == piece.getTeam()
                ) {
                    result.add(nowSquare);
                }
            } catch (NotExistSquareException e) {
                continue;
            }
        }
        return result;
    }
}
