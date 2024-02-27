package ru.est0y.diceChess.config.mongo.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import ru.est0y.diceChess.domain.Square;
import ru.est0y.diceChess.services.squares.SquareCache;

@Component
@RequiredArgsConstructor
@ReadingConverter
public class StringToSquareConverter implements Converter<String, Square> {
    private final SquareCache squareCache;

    @Override
    public Square convert(String source) {
        // Парсинг строки и создание объекта Square
        String[] parts = source.substring(1, source.length() - 1).split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return squareCache.getSquare(x,y);
    }
}
