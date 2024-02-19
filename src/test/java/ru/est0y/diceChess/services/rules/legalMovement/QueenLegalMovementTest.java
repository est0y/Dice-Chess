package ru.est0y.diceChess.services.rules.legalMovement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.services.rules.legalMovement.QueenLegalMovement;
import ru.est0y.diceChess.services.fen.FenUtils;

@SpringBootTest
class QueenLegalMovementTest {
    @Autowired
    private QueenLegalMovement queenLegalMovement;
    @Autowired
    private FenUtils fenUtils;

    @Test
    void getAllLegalMoves() {
        var piece = new Queen(1);
        var from = new Square(4, 4);
        var board = fenUtils.fenFromString("8/8/1n1n1n2/8/2NQN3/2NNN3/8/8 w - - 0 1");
        System.out.println(queenLegalMovement.getAllLegalMoves(from, piece, board));
    }
}