package ru.est0y.diceChess.services.rules.piecesPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Rook;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilter;

@RequiredArgsConstructor
@Service
public class RookPipeline implements PiecePipeline<Rook> {

    private final PieceMovementFilter<Rook> rookMovementFilter;

    private final BoardUtils boardUtils;

    @Override
    public void makeMove(Rook piece, Move move, Game game) {
        rookMovementFilter.doFilter(piece,move, game.getBoard());
        boardUtils.makeMove(move, game.getBoard());
    }
}
