package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.services.directions.MoveDirectionManager;
import ru.est0y.diceChess.domain.board.Board;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BishopLegalMovement implements LegalMovement<Bishop> {

    private final MoveDirectionManager moveDirectionManager;

    @Override
    public List<Square> getAllLegalMoves(Square square, Bishop piece, Board board) {
        var directions = Arrays.stream(Direction.values()).filter(Direction::isDiagonal).toList();
        return directions.stream()
                .flatMap(d -> moveDirectionManager.moveToPossible(square, d, board).stream()).toList();
    }
}
