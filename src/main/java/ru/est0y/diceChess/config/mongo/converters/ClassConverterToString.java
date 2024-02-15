package ru.est0y.diceChess.config.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import ru.est0y.diceChess.domain.AbstractPiece;

@WritingConverter
public class ClassConverterToString implements Converter<Class<? extends AbstractPiece>, String> {
    @Override
    public String convert(Class<? extends AbstractPiece> source) {
        return source.getSimpleName();
    }
}
