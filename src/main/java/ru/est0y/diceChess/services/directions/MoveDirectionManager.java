package ru.est0y.diceChess.services.directions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MoveDirectionManager {

    private final EmptySquareDirection emptySquareDirection;

    private final BoardNavigator boardNavigator;

    public List<Square> moveToPossible(Square square, Direction direction, Board board) {
        List<Square> squares = emptySquareDirection.getEmptyToOccupied(square, direction, board);
        Square lastEmpty = squares.isEmpty() ? square : squares.get(squares.size() - 1);
        Square occupatedSquare;
        try {
            occupatedSquare = boardNavigator.nextSquare(lastEmpty, direction, board.getSize());
        } catch (NotExistSquareException e) {
            return squares;
        }
        var piece = board.getPiece(square);
        if (board.getPiece(occupatedSquare).orElseThrow().getTeam() != piece.orElseThrow().getTeam()) {
            squares.add(occupatedSquare);
        }
        return squares;
    }

    public List<Square> moveToPossible(Square square, Direction direction, Board board, int maxPath) {
        List<Square> squares = emptySquareDirection.getEmptyToOccupied(square, direction, board, maxPath);
        Square lastEmpty = squares.isEmpty() ? square : squares.get(squares.size() - 1);
        if (squares.size() == maxPath) {
            return squares;
        }
        Square occupatedSquare;
        try {
            occupatedSquare = boardNavigator.nextSquare(lastEmpty, direction, board.getSize());
        } catch (NotExistSquareException e) {
            return squares;
        }
        var piece = board.getPiece(square);
        if (board.getPiece(occupatedSquare).orElseThrow().getTeam() != piece.orElseThrow().getTeam()) {
            squares.add(occupatedSquare);
        }
        return squares;
    }
}
