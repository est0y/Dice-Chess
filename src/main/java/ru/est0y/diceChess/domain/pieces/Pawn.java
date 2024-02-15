package ru.est0y.diceChess.domain.pieces;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceCreator;
import ru.est0y.diceChess.domain.AbstractPiece;

@Getter
@Setter
@EqualsAndHashCode
public class Pawn extends AbstractPiece {
    private boolean isMoved = false;


    public Pawn(int team) {
        super(team);
    }

    @PersistenceCreator
    public Pawn(int team, boolean isMoved) {
        super(team);
        this.isMoved = isMoved;
    }
}
