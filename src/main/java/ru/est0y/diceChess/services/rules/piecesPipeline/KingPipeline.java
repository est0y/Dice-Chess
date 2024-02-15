package ru.est0y.diceChess.services.rules.piecesPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilter;

@RequiredArgsConstructor
@Service
public class KingPipeline implements PiecePipeline<King> {

    private final PieceMovementFilter<King> kingMovementFilter;

    private final BoardUtils boardUtils;

    @Override
    public void makeMove(King king, Move move, Game game) {
        kingMovementFilter.doFilter(king, move, game.getBoard());
        boardUtils.makeMove(move, game.getBoard());
    }
}
