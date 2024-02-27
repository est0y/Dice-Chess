package ru.est0y.diceChess.services.diceChess;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.domain.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AfterMove {
    private static final Random RANDOM = new Random();

    private static final List<Class<? extends AbstractPiece>> PIECE_CLASSES = List.of(
            Pawn.class,
            Rook.class,
            Knight.class,
            Bishop.class,
            Queen.class,
            King.class
    );

    public void deleteDice(Move move, Game game) {
        var board = game.getBoard();
        var pieceClass = board.getPiece(move.getTo()).orElseThrow().getClass();
        game.getLegalPieces().remove(pieceClass);
    }

    public void endMove(Game game) {
        var turnHolderTeam = game.getTurnHolder().getTeamNumber();
        var newTurnHolder = game.getPlayers().stream()
                .filter(p -> p.getTeamNumber() != turnHolderTeam).findFirst().orElseThrow();
        game.setTurnHolder(newTurnHolder);
        game.setLegalPieces(new ArrayList<>());
        var die1 = PIECE_CLASSES.get(RANDOM.nextInt(PIECE_CLASSES.size()));
        var die2 = PIECE_CLASSES.get(RANDOM.nextInt(PIECE_CLASSES.size()));
        game.getLegalPieces().add(die1);
        game.getLegalPieces().add(die2);
    }
}
