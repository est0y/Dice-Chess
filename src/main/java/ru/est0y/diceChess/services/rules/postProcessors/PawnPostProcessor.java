package ru.est0y.diceChess.services.rules.postProcessors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.services.IllegalMoveException;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.rules.piecesPipeline.BoardUtils;

@Service
@RequiredArgsConstructor
public class PawnPostProcessor implements PiecePostProcessor<Pawn> {

    private final BoardUtils boardUtils;


    @Override
    public void process(Pawn piece, Move move, Board board) {
        if (boardUtils.isLastHorizontal(move.getTo(), board)) {
            var promotion = move.getPromotion();
            if (promotion.isPresent()) {
                board.putPiece(move.getTo(), promotion.get());
            } else {
                throw new IllegalMoveException();
            }
        }
    }


}
