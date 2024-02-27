package ru.est0y.diceChess.services.fen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Player;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.fen.FenUtils;
import ru.est0y.diceChess.services.PlayersPiecesUtils;
import ru.est0y.diceChess.domain.board.DiceChessBoardImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FenUtilsTest {
    @Autowired
    private PlayersPiecesUtils playersPiecesUtils;

    @Autowired
    private FenUtils fenUtils;


    @Test
    void fenFromString() {
        var board = fenUtils.fenFromString("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1");
        System.out.println(board);
        Assertions.assertEquals(new Pawn(1),board.getPiece(new Square(5,4)).orElseThrow());
    }

    @Test
    void fenFromGame() {
        var board = fenUtils.fenFromLichessUrl(
                "https://lichess.org/editor/rnbqkbnr/ppppppp1/7p/8/8/3B4/PPPPPPPP/R1BQNBN1_w_HAkq_-_0_1?color=white");
        var game = new Game("1", null,
                new Player("user", 1),
                new ArrayList<>(List.of(Knight.class, Pawn.class)), new DiceChessBoardImpl(board, playersPiecesUtils));
        var result = fenUtils.fenFromGame(game);
        System.out.println(result);
    }
}