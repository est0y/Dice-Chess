package ru.est0y.diceChess.services.rules.piecesPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilter;

@RequiredArgsConstructor
@Service
public class QueenPipeline implements PiecePipeline<Queen> {

    private final PieceMovementFilter<Queen> queenPieceMovementFilter;

    private final BoardUtils boardUtils;

    @Override
    public void makeMove(Queen piece, Move move, Game game) {
        queenPieceMovementFilter.doFilter(piece, move, game.getBoard());
        boardUtils.makeMove(move, game.getBoard());
    }
}
