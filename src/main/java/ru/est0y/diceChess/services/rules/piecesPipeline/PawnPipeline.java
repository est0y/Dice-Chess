package ru.est0y.diceChess.services.rules.piecesPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.services.rules.postProcessors.PiecePostProcessor;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilter;

@Service
@RequiredArgsConstructor
public class PawnPipeline implements PiecePipeline<Pawn> {

    private final PieceMovementFilter<Pawn> pawnRule;

    private final PiecePostProcessor<Pawn> pawnPostProcessor;

    private final BoardUtils boardUtils;

    @Override
    public void makeMove(Pawn piece, Move move, Game game) {
        pawnRule.doFilter(piece,move, game.getBoard());
        boardUtils.makeMove(move, game.getBoard());
        pawnPostProcessor.process(piece,move, game.getBoard());

    }
}
