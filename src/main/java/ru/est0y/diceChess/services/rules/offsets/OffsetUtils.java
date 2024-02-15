package ru.est0y.diceChess.services.rules.offsets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Offset;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.services.squares.SquareCache;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OffsetUtils {
    private final SquareCache squareCache;

    public List<Square> apply(Square from, List<Offset> offsets) {
        var x = from.getX();
        var y = from.getY();
        List<Square> squares = new ArrayList<>();
        for (var offset : offsets) {
            try {
                var square = squareCache.getSquare(x + offset.getX(), y + offset.getY());
                if (square != null) {
                    squares.add(squareCache.getSquare(x + offset.getX(), y + offset.getY()));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        return squares;
    }
}
