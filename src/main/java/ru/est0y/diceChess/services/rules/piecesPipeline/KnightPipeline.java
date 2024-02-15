package ru.est0y.diceChess.services.rules.piecesPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilter;

@RequiredArgsConstructor
@Service
public class KnightPipeline implements PiecePipeline<Knight> {

    private final PieceMovementFilter<Knight> knightPieceMovementFilter;

    private final BoardUtils boardUtils;

    @Override
    public void makeMove(Knight piece, Move move, Game game) {
        knightPieceMovementFilter.doFilter(piece,move, game.getBoard());
        boardUtils.makeMove(move, game.getBoard());
    }
}
