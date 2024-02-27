package ru.est0y.diceChess.services.rules.movementFilters;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.services.diceChess.MoveFilter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PieceMovementFilterManager implements MoveFilter {
    private final Map<Class<? extends AbstractPiece>, PieceMovementFilter> pieceMovementFilters;

    public PieceMovementFilterManager(List<PieceMovementFilter<?>> pieceMovementFilters) {

        this.pieceMovementFilters = pieceMovementFilters
                .stream().collect(Collectors.toMap(PieceMovementFilter::getType, (p) -> p));
    }

    @Override
    public void doFilter(Move move, Game game) {
        var board = game.getBoard();
        AbstractPiece piece = board.getPiece(move.getFrom())
                .orElseThrow(() -> new RuntimeException("Piece is not exist"));
        var filter = pieceMovementFilters.get(piece.getClass());
        filter.doFilter(piece, move, board);
    }
}
