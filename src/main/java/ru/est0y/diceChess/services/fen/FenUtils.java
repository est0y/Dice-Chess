package ru.est0y.diceChess.services.fen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.config.props.GameProps;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;
import ru.est0y.diceChess.domain.board.BoardImpl;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.domain.pieces.Rook;
import ru.est0y.diceChess.services.piecesManagement.PieceCreatorManager;
import ru.est0y.diceChess.services.squares.SquareCache;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class FenUtils {
    private static final Map<Class<? extends AbstractPiece>, Character> PIECE_LETTERS = Map.of(
            Bishop.class, 'b',
            Knight.class, 'n',
            King.class, 'k',
            Queen.class, 'q',
            Rook.class, 'r',
            Pawn.class, 'p'
    );

    private final PieceCreatorManager pieceCreatorManager;

    private final SquareCache squareCache;

    private final GameProps gameProps;

    public int getTeam(String fen) {
        var fenParts = fen.split(" ");
        String color = fenParts[1];
        return color.equals("w") ? 1 : 2;
    }

    public Board fenFromString(String fen) {
        var fenParts = fen.split(" ");
        var horizontals = fenParts[0].split("/");
        var color = fenParts[1];
        var castling = fenParts[2];
        var squareTake = fenParts[3];
        var num1 = fenParts[4];
        var num2 = fenParts[5];
        var map = new HashMap<Square, AbstractPiece>();
        for (int row = horizontals.length, counter = 0; row != 0; row--, counter++) {
            char[] chars = horizontals[counter].toCharArray();
            int col = 0;
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    col += Character.getNumericValue(c);
                } else {
                    int team = Character.isLowerCase(c) ? 2 : 1;
                    Square square = new Square(col + 1, row);
                    map.put(square, pieceCreatorManager.create(Character.toLowerCase(c), team));
                    col++;
                }
            }
        }

        return new BoardImpl(map,gameProps.getBoardSize());
    }


    public Board fenFromLichessUrl(String url) {
        String fen = url.replace("https://lichess.org/editor/", "")
                .replace("_", " ");

        int questionMarkIndex = fen.indexOf('?');
        fen = questionMarkIndex != -1 ? fen.substring(0, questionMarkIndex) : fen;
        return fenFromString(fen);
    }

    public String fenFromGame(Game game) {
        String result = "";
        for (var y = 8; y > 0; y--) {
            int emptySquaresCount = 0;
            for (var x = 1; x <= 8; x++) {
                var board = game.getBoard();
                Square square = squareCache.getSquare(x, y);
                var optionalPiece = board.getPiece(square);
                if (optionalPiece.isPresent()) {
                    result = emptySquaresCount > 0 ? result.concat(String.valueOf(emptySquaresCount)) : result;
                    emptySquaresCount = 0;
                    var piece = optionalPiece.get();
                    var letter = PIECE_LETTERS.get(piece.getClass());
                    letter = piece.getTeam() == 1 ? Character.toUpperCase(letter) : letter;
                    result = result.concat(String.valueOf(letter));
                } else {
                    emptySquaresCount++;
                }
            }
            result = emptySquaresCount > 0 ? result.concat(String.valueOf(emptySquaresCount)) : result;
            result = result.concat("/");
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
