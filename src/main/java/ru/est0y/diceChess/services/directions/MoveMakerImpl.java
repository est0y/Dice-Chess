package ru.est0y.diceChess.services.directions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;

@Service
@Slf4j
public class MoveMakerImpl implements MoveMaker {
    @Override
    public Board makeMove(Move move, Board board) {
        makeMove(move.getFrom(), move.getTo(), board);
        if (move.getPromotion().isPresent()) {
            board.putPiece(move.getTo(), move.getPromotion().get());
        }
        return board;
    }

    @Override
    public Board makeMove(Square from, Square to, Board board) {

        var piece = board.getPiece(from).orElseThrow();
        board.remove(to);
        board.remove(from);
        board.putPiece(to, piece);
        return board;
    }
}
