package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Direction;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.services.directions.BoardNavigator;
import ru.est0y.diceChess.services.directions.MoveDirectionManager;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;
import ru.est0y.diceChess.services.rules.BoardUtils;
import ru.est0y.diceChess.domain.board.Board;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KingLegalMovement implements LegalMovement<King> {


    private final BoardNavigator chessBoardNavigator;

    private final BoardUtils boardUtils;

    private final PieceRulesUtils pieceRulesUtils;

    private final MoveDirectionManager moveDirectionManager;

    @Override
    public List<Square> getAllLegalMoves(Square square, King piece, Board board) {
        var directions = Arrays.stream(Direction.values()).filter(d -> d.isOrthogonal() || d.isDiagonal()).toList();
        return directions.stream().flatMap(d -> moveDirectionManager.moveToPossible(square, d, board, 1)
                .stream()).toList();
       /* return directions.stream()
                .map(d -> chessBoardNavigator
                        .nextSquare(square, d, board.getSize())).filter(s -> boardUtils.isSquareExists(s, board))
                .filter(s -> pieceRulesUtils.isNotFriendlyFire(new Move(square, s), board))
                .toList();*/
    }
}
