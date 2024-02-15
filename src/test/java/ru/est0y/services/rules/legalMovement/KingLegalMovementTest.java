package ru.est0y.services.rules.legalMovement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.services.rules.legalMovement.KingLegalMovement;
import ru.est0y.diceChess.services.fen.FenUtils;

@SpringBootTest
class KingLegalMovementTest {
    @Autowired
    private KingLegalMovement kingLegalMovement;

    @Autowired
    private FenUtils fenUtils;


    @Test
    void getAllLegalMoves() {
        var piece = new King(1);
        var from = new Square(3, 1);
        var board = fenUtils.fenFromString("8/8/8/8/8/8/8/2K5 w - - 0 1");
        System.out.println(kingLegalMovement.getAllLegalMoves(from,piece,board));
    }
}