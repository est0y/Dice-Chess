package ru.est0y.diceChess.services.rules.legalMovement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.services.rules.legalMovement.PawnLegalMovement;
import ru.est0y.diceChess.services.fen.FenUtils;

@SpringBootTest
class PawnLegalMovementTest {

    @Autowired
    private PawnLegalMovement pawnLegalMovement;

    @Autowired
    private FenUtils fenUtils;


    @Test
    void getAllLegalMoves() {
        var piece = new Pawn(1,false);
        var from = new Square(4, 2);
        var board = fenUtils.fenFromString("8/8/8/8/3n4/4n3/3P4/8 w - - 0 1");
        System.out.println(pawnLegalMovement.getAllLegalMoves(from,piece,board));
    }
}