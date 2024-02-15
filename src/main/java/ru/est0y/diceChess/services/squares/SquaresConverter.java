package ru.est0y.diceChess.services.squares;

import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.services.directions.NotExistSquareException;

import java.util.HashMap;
import java.util.Map;

@Service
public class SquaresConverter {
    private final Map<String, Square> squaresByString;

    public SquaresConverter(SquareCache squareCache) {
        var map = new HashMap<String, Square>();
        for (var letter : Letters.values()) {
            for (var y = 1; y <= 8; y++) {
                String string = String.valueOf(letter.getLetter()) + y;
                map.put(string, squareCache.getSquare(letter.getNumber(), y));
            }
        }
        this.squaresByString = map;
    }

    public Square getSquare(String string) {
        return squaresByString.get(string);
    }

    public Move getMove(String string) {
        var array = string.split("(?=[a-zA-Z]+\\d)");
        var from = squaresByString.get(array[0]);
        var to = squaresByString.get(array[1]);
        if (from == null) {
            throw new NotExistSquareException(array[0]);
        }
        if (to == null) {
            throw new NotExistSquareException(array[1]);
        }
        return new Move(squaresByString.get(array[0]), squaresByString.get(array[1]));
    }
}
