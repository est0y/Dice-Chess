package ru.est0y.diceChess.services.diceChess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.App;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Player;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.DiceChessBoardImpl;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.services.diceChess.GameStateFilter;
import ru.est0y.diceChess.services.PlayersPiecesUtils;
import ru.est0y.diceChess.services.fen.FenUtils;


import java.util.List;

@SpringBootTest

class GameStateFilterTest {
    @Autowired
    private FenUtils fenUtils;

    @Autowired
    private PlayersPiecesUtils playersPiecesUtils;

    @Autowired
    private GameStateFilter gameStateFilter;



    @Test
    void doFilter() {

        var board = fenUtils.fenFromLichessUrl(
                "https://lichess.org/editor/rnbqkbnr/pppppppp/8/8/6P1/R1PPPPP1/PPP1PPBP/RNBP1QNR_w_HAkq_-_0_1?color=white");
        var playerSquares = playersPiecesUtils.getAllPlayerPieces(1, board);
        var diceChessBoard = new DiceChessBoardImpl(board, playersPiecesUtils);
        System.out.println(diceChessBoard);
        var player = new Player("1", 1);
        var move = new Move(new Square(3, 1), new Square(4, 2));
        var game = new Game(null, null, player, List.of(King.class, Bishop.class), diceChessBoard);
        gameStateFilter.doFilter(move, game);
    }
}