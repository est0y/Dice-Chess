package ru.est0y.services.rules.legalMovement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.services.rules.legalMovement.KnightLegalMovement;
import ru.est0y.diceChess.services.fen.FenUtils;

@SpringBootTest
class KnightLegalMovementTest {

    @Autowired
    private KnightLegalMovement kingLegalMovement;

    @Autowired
    private FenUtils fenUtils;

    @Test
    void getAllLegalMoves() {
        var piece = new Knight(1);
        var from = new Square(4, 3);
        var board = fenUtils.fenFromString("8/8/8/8/8/3N4/8/8 w - - 0 1");
        System.out.println(kingLegalMovement.getAllLegalMoves(from, piece, board));
    }
}
