package ru.est0y.services.rules.legalMovement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.services.rules.legalMovement.PotentialMoves;
import ru.est0y.diceChess.services.fen.FenUtils;

import java.util.List;

@SpringBootTest
class PotentialMovesTest {

    @Autowired
    private PotentialMoves potentialMoves;

    @Autowired
    private FenUtils fenUtils;

    @Test
    void getQueenPotentialMoves() {
        var board = fenUtils.fenFromString("rnbqkbnr/pppppppp/8/8/6P1/8/PPPPPP1P/RNBQKBNR w KQkq - 0 1");
        System.out.println(potentialMoves.getPotentialMoves(new Square(4, 1), new Queen(1), board,
                List.of(Pawn.class, King.class)));
    }

    @Test
    void getKnightPotentialMoves() {
        var board = fenUtils.fenFromString("rnbqkbnr/pppppppp/8/8/6P1/8/PPPPPP1P/RNBQKBNR w KQkq - 0 1");
        System.out.println(potentialMoves.getPotentialMoves(new Square(7, 1), new Knight(1), board, List.of(Pawn.class, King.class)));
    }
    @Test
    void getPawnPotentialMoves() {

        var board = fenUtils.fenFromString("rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R w KQkq - 0 1");
        System.out.println(potentialMoves.getPotentialMoves(new Square(6, 2), new Pawn(1), board, List.of(Knight.class)));
    }
}