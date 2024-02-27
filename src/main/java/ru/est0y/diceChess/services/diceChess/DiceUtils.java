package ru.est0y.diceChess.services.diceChess;

import lombok.RequiredArgsConstructor;
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
import ru.est0y.diceChess.services.rules.legalMovement.LegalMovementManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DiceUtils {

    private static final List<Class<? extends AbstractPiece>> PIECE_CLASSES = List.of(
            Pawn.class,
            Rook.class,
            Knight.class,
            Bishop.class,
            Queen.class,
            King.class
    );

    private static final Random RANDOM = new Random();

    private final LegalMovementManager legalMovementManager;


    public boolean isNotExistsMoves(Game game) {
        var player = game.getTurnHolder();
        var die = game.getLegalPieces();
        var board = game.getBoard();
        var playerPieces = board.getPlayerPieces(player.getTeamNumber());
        return die.stream().noneMatch(clazz -> playerPieces.get(clazz).stream()
                .anyMatch(p -> !legalMovementManager.getAllLegalMoves(p.getSquare(), board).isEmpty()));
    }

    public void deleteDice(Move move, Game game) {
        var board = game.getBoard();
        var pieceClass = board.getPiece(move.getTo()).orElseThrow().getClass();
        game.getLegalPieces().remove(pieceClass);
    }

    public void rollDices(Game game) {
        game.setLegalPieces(new ArrayList<>());
        var die1 = PIECE_CLASSES.get(RANDOM.nextInt(PIECE_CLASSES.size()));
        var die2 = PIECE_CLASSES.get(RANDOM.nextInt(PIECE_CLASSES.size()));
        game.getLegalPieces().add(die1);
        game.getLegalPieces().add(die2);
    }
}
