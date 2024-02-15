package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Rook;
import ru.est0y.diceChess.services.directions.MoveDirectionManager;
import ru.est0y.diceChess.domain.board.Board;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RookLegalMovement implements LegalMovement<Rook> {

    private final MoveDirectionManager moveDirectionManager;

    @Override
    public List<Square> getAllLegalMoves(Square square, Rook piece, Board board) {
        var directions = Arrays.stream(Direction.values()).filter(Direction::isOrthogonal).toList();
        return directions.stream()
                .flatMap(d -> moveDirectionManager.moveToPossible(square, d, board).stream()).toList();
    }
}
