package ru.est0y.diceChess.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.dice.PieceLocation;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.domain.pieces.Rook;
import ru.est0y.diceChess.services.squares.SquareCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlayersPiecesUtils {

    private static final List<Class<? extends AbstractPiece>> PIECE_CLASSES = List.of(
            Pawn.class,
            Rook.class,
            Knight.class,
            Bishop.class,
            Queen.class,
            King.class
    );

    private final SquareCache squareCache;

    public Map<Class<? extends AbstractPiece>, List<Square>> getAllPlayerPieces(int team, Board board) {
        Map<Class<? extends AbstractPiece>, List<Square>> result = new HashMap<>();
        var boardSize = board.getSize();
        for (var x = 1; x <= boardSize.getHorizontalSize(); x++) {
            for (var y = 1; y <= boardSize.getVerticalSize(); y++) {
                var square = squareCache.getSquare(x, y);
                var optionalPiece = board.getPiece(square);
                if (optionalPiece.isPresent() && optionalPiece.get().getTeam() == team) {
                    var clazz = optionalPiece.get().getClass();
                    result.computeIfAbsent(clazz, (k) -> new ArrayList<>());
                    result.get(clazz).add(square);
                }
            }
        }
        return result;
    }

    public Map<Class<? extends AbstractPiece>, List<PieceLocation>> getPlayerPiecesLocation(int team, Board board) {
        Map<Class<? extends AbstractPiece>, List<PieceLocation>> result = new HashMap<>();
        PIECE_CLASSES.forEach(c -> result.computeIfAbsent(c, (k) -> new ArrayList<>()));
        var boardSize = board.getSize();
        for (var x = 1; x <= boardSize.getHorizontalSize(); x++) {
            for (var y = 1; y <= boardSize.getVerticalSize(); y++) {
                var square = squareCache.getSquare(x, y);
                var optionalPiece = board.getPiece(square);
                if (optionalPiece.isPresent() && optionalPiece.get().getTeam() == team) {
                    var clazz = optionalPiece.get().getClass();
                    result.get(clazz).add(new PieceLocation(square, board.getPiece(square).orElseThrow()));
                }
            }
        }
        return result;
    }
}
