package ru.est0y.diceChess.services.rules.legalMovement;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.Square;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LegalMovementManager {
    private final Map<Class<? extends AbstractPiece>, LegalMovement> legalMovements;


    public LegalMovementManager(List<LegalMovement<?>> legalMovements) {
        this.legalMovements = legalMovements.stream().collect(Collectors.toMap(LegalMovement::getType, lm -> lm));
    }

    public List<Square> getAllLegalMoves(Square square, Board board) {
        var piece = board.getPiece(square).orElseThrow();
        return legalMovements.get(piece.getClass()).getAllLegalMoves(square, piece, board);
    }
}
