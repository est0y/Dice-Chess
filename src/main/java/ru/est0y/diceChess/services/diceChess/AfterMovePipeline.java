package ru.est0y.diceChess.services.diceChess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Pawn;

@Service
@RequiredArgsConstructor
public class AfterMovePipeline {

    private final AfterMove afterMove;

    private final DiceUtils diceUtils;


    public void afterMove(Move move, Game game) {
        var piece = game.getBoard().getPiece(move.getTo()).orElseThrow();
        if (piece.getClass().equals(Pawn.class)) {
            Pawn pawn = (Pawn) piece;
            pawn.setMoved(true);
        }
        diceUtils.deleteDice(move, game);
        if (game.getLegalPieces().isEmpty()) {
            afterMove.endMove(game);
        }
        boolean isNotExistsMoves = !diceUtils.isExistsMoves(game);
        while (isNotExistsMoves) {
            afterMove.endMove(game);
            isNotExistsMoves = !diceUtils.isExistsMoves(game);
        }
    }
}
