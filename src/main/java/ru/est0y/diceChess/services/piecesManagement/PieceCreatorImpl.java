package ru.est0y.diceChess.services.piecesManagement;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.domain.pieces.Rook;

import java.util.Map;

@Service
public class PieceCreatorImpl implements PieceCreatorManager {
    private static final Map<Character, PieceCreator<?>> PIECE_CREATORS = Map.of(
            'b', Bishop::new,
            'n', Knight::new,
            'k', King::new,
            'q', Queen::new,
            'r', Rook::new,
            'p', Pawn::new
    );

    @Override
    public AbstractPiece create(char letter, int team) {
        return PIECE_CREATORS.get(letter).create(team);
    }
}
