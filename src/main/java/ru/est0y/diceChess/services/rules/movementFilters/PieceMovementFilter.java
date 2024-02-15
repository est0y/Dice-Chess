package ru.est0y.diceChess.services.rules.movementFilters;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.board.Board;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public interface PieceMovementFilter<T extends AbstractPiece> {
    void doFilter(T piece, Move move, Board board);

    default Class<T> getType() {
        var intf = Arrays.stream(getClass().getGenericInterfaces()).findFirst().orElseThrow();
        ParameterizedType parameterizedType = (ParameterizedType) intf;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        return (Class<T>) typeArguments[0];
    }

}
