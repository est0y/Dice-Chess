package ru.est0y.diceChess.services.directions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmptySquareDirection {
    private final BoardNavigator boardNavigator;

    public List<Square> getEmptyToOccupied(Square square, Direction direction, Board board) {
        List<Square> squares = new ArrayList<>();
        Square nowSquare = getNextEmptySquare(square, direction, board);

        while (nowSquare != null) {
            squares.add(nowSquare);
            nowSquare = getNextEmptySquare(nowSquare, direction, board);
        }

        return squares;
    }

    public List<Square> getEmptyToOccupied(Square square, Direction direction, Board board, int maxPath) {
        List<Square> squares = new ArrayList<>();

        for (int i = 1; i <= maxPath; i++) {
            Square nowSquare = getNextEmptySquare(square, direction, board);
            if (nowSquare == null) {
                break;
            }

            squares.add(nowSquare);
            square = nowSquare;
        }

        return squares;
    }


    public boolean isPathClear(Move move, Direction direction, Board board) {
        var from = move.getFrom();
        var to = move.getTo();
        var square = boardNavigator.nextSquare(from, direction, board.getSize());
        if (to.equals(square)) {
            return true;
        }
        for (var nowSquare = square;
             !to.equals(nowSquare);
             nowSquare = boardNavigator.nextSquare(nowSquare, direction, board.getSize())) {
            if (board.getPiece(nowSquare).isPresent()) {
                return false;
            }
        }
        return true;
    }

    private Square getNextEmptySquare(Square square, Direction direction, Board board) {
        try {
            Square nextSquare = boardNavigator.nextSquare(square, direction, board.getSize());
            if (board.getPiece(nextSquare).isEmpty()) {
                return nextSquare;
            }
        } catch (NotExistSquareException | NullPointerException e) {
           return null;
        }
        return null;
    }
}
