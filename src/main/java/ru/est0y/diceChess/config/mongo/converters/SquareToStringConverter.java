package ru.est0y.diceChess.config.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import ru.est0y.diceChess.domain.Square;

@WritingConverter
public class SquareToStringConverter implements Converter<Square, String> {
    @Override
    public String convert(Square square) {
        return "(" + square.getX() + "," + square.getY() + ")";
    }
}