package ru.est0y.diceChess.services.rules.legalMovement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Offset;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.services.directions.BoardNavigator;
import ru.est0y.diceChess.services.rules.PieceRulesUtils;
import ru.est0y.diceChess.services.rules.BoardUtils;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.services.rules.offsets.OffsetUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KnightLegalMovement implements LegalMovement<Knight> {

    public static final List<Offset> OFFSETS = List.of(
            new Offset(-1, -2),
            new Offset(-2, -1),
            new Offset(1, -2),
            new Offset(2, -1),
            new Offset(-2, 1),
            new Offset(-1, 2),
            new Offset(1, 2),
            new Offset(2, 1)
    );

    private final BoardNavigator chessBoardNavigator;

    private final BoardUtils boardUtils;

    private final PieceRulesUtils pieceRulesUtils;

    private final OffsetUtils offsetUtils;


    @Override
    public List<Square> getAllLegalMoves(Square square, Knight piece, Board board) {
            return offsetUtils.apply(square,OFFSETS).stream().filter(s -> boardUtils.isSquareExists(s, board))
                    .filter(s -> pieceRulesUtils.isNotFriendlyFire(square, s, board)).toList();
/*        return OFFSETS.stream()
                .map(offset -> new Square(square.getX() + offset.getX(), square.getY() + offset.getY()))
                .filter(s -> boardUtils.isSquareExists(s, board))
                .filter(s -> pieceRulesUtils.isNotFriendlyFire(square, s, board)).toList();*/
    }
}
