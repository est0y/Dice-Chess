package ru.est0y.diceChess.domain;

import lombok.Getter;

@Getter
public enum Direction {
    UP(true, false),
    DOWN(true, false),
    LEFT(true, false),
    RIGHT(true, false),
    LEFT_UP(false,true),
    LEFT_DOWN(false,true),
    RIGHT_UP(false,true),
    RIGHT_DOWN(false,true),
    UNKNOWN(false,false);

    private final boolean isOrthogonal;

    private final boolean isDiagonal;

    Direction(boolean isOrthogonal, boolean isDiagonal) {
        this.isOrthogonal = isOrthogonal;
        this.isDiagonal = isDiagonal;
    }
}
