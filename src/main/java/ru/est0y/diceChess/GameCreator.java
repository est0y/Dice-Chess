package ru.est0y.diceChess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Player;
import ru.est0y.diceChess.domain.board.DiceChessBoardImpl;
import ru.est0y.diceChess.services.PlayersPiecesUtils;
import ru.est0y.diceChess.services.diceChess.AfterMove;
import ru.est0y.diceChess.services.diceChess.DiceUtils;
import ru.est0y.diceChess.services.fen.FenUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameCreator {
    private final FenUtils fenUtils;

    private final PlayersPiecesUtils playersPiecesUtils;

    private final DiceUtils diceUtils;

    private final AfterMove afterMove;

    public Game createGame(String gameId, String playerUserName1, String playerUserName2, String fen) {
        var player1 = new Player(playerUserName1, 1);
        var player2 = new Player(playerUserName2, 2);
        var board = fenUtils.fenFromString(fen);
        var diceChessBoard = new DiceChessBoardImpl(board, playersPiecesUtils);
        var game = new Game(gameId, List.of(player1, player2), player1, new ArrayList<>(), diceChessBoard);
        diceUtils.rollDices(game);
        boolean isNotExistsMoves = diceUtils.isNotExistsMoves(game);
        while (isNotExistsMoves) {
            afterMove.endMove(game);
            isNotExistsMoves = diceUtils.isNotExistsMoves(game);
        }
        return game;
    }
}
