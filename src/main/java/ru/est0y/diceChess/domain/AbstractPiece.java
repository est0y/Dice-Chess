package ru.est0y.diceChess.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public abstract class AbstractPiece {
    private final int team;

    public int getTeam() {
        return team;
    }
}
