package ru.est0y.diceChess.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BoardSize {
    private final int horizontalSize;

    private final int verticalSize;
}
