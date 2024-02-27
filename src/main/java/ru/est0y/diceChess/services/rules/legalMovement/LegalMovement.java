package ru.est0y.diceChess.services.rules.legalMovement;

import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.domain.board.Board;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public interface LegalMovement<T extends AbstractPiece> {
    List<Square> getAllLegalMoves(Square square, T piece, Board board);

    default Class<T> getType() {
        var intf = Arrays.stream(getClass().getGenericInterfaces()).findFirst().orElseThrow();
        ParameterizedType parameterizedType = (ParameterizedType) intf;
        Type rawType = parameterizedType.getRawType();
        //  if (rawType instanceof Class<?> && PieceRule.class.isAssignableFrom((Class<?>) rawType))continue;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        return (Class<T>) typeArguments[0];
        //  return (Class<T>) ;
    }
}
