package ru.est0y.diceChess.domain.messages;

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
public class PieceMapping {
    private static final Map<Class<? extends AbstractPiece>, Piece> CLASSES = Map.of(
            Pawn.class, Piece.P,
            Rook.class, Piece.R,
            Knight.class, Piece.N,
            Bishop.class, Piece.B,
            Queen.class, Piece.Q,
            King.class, Piece.K
    );

    public Piece getPiece(Class<? extends AbstractPiece> clazz) {
        return CLASSES.get(clazz);
    }
}
