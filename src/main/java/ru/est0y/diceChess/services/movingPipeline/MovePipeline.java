package ru.est0y.diceChess.services.movingPipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.services.GameService;
import ru.est0y.diceChess.services.diceChess.AfterMovePipeline;
import ru.est0y.diceChess.services.diceChess.GameStateFilter;
import ru.est0y.diceChess.services.diceChess.LegalPiecesFilter;
import ru.est0y.diceChess.services.directions.MoveMaker;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.services.messaging.GameMessagingService;
import ru.est0y.diceChess.services.rules.movementFilters.PieceMovementFilterManager;

@RequiredArgsConstructor
@Service
public class MovePipeline {
    private final GameService gameService;

    private final MoveMaker moveMaker;

    private final PieceMovementFilterManager pieceMovementFilterManager;

    private final LegalPiecesFilter legalPiecesFilter;

    private final GameStateFilter diceChessFilter;

    private final AfterMovePipeline afterMovePipeline;

    private final GameMessagingService gameMessagingService;

   public void run(Move move, Game game) {
        legalPiecesFilter.doFilter(move, game);
        pieceMovementFilterManager.doFilter(move, game);
        diceChessFilter.doFilter(move,game);
        moveMaker.makeMove(move, game.getBoard());
        afterMovePipeline.afterMove(move,game);
        gameService.save(game);
        gameMessagingService.sendToAll(move,game);
    }
}
