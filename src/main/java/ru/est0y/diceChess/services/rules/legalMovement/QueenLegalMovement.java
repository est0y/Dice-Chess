package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.services.directions.MoveDirectionManager;
import ru.est0y.diceChess.domain.board.Board;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueenLegalMovement implements LegalMovement<Queen> {

    private final MoveDirectionManager moveDirectionManager;

    @Override
    public List<Square> getAllLegalMoves(Square square, Queen piece, Board board) {
        var directions = Arrays.stream(Direction.values()).filter(d -> d.isOrthogonal() || d.isDiagonal()).toList();
        return directions.stream()
                .flatMap(d -> moveDirectionManager.moveToPossible(square, d, board).stream()).toList();
    }
}
