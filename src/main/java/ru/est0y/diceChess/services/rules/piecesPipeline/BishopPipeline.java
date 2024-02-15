package ru.est0y.diceChess.services.rules.piecesPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilter;

@RequiredArgsConstructor
@Service
public class BishopPipeline implements PiecePipeline<Bishop> {

    private final PieceMovementFilter<Bishop> bishopPieceMovementFilter;

    private final BoardUtils boardUtils;

    @Override
    public void makeMove(Bishop bishop, Move move, Game game) {
        bishopPieceMovementFilter.doFilter(bishop, move, game.getBoard());
        boardUtils.makeMove(move, game.getBoard());
    }
}
