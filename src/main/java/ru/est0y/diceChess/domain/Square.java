package ru.est0y.diceChess.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;



@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Square {
    @Min(1)
    @Max(8)
    private final int x;

    @Min(1)
    @Max(8)
    private final int y;
}


