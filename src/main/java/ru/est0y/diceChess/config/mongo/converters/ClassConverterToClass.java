package ru.est0y.diceChess.config.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.pieces.Bishop;
import ru.est0y.diceChess.domain.pieces.King;
import ru.est0y.diceChess.domain.pieces.Knight;
import ru.est0y.diceChess.domain.pieces.Pawn;
import ru.est0y.diceChess.domain.pieces.Queen;
import ru.est0y.diceChess.domain.pieces.Rook;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ReadingConverter
public class ClassConverterToClass implements Converter<String, Class<? extends AbstractPiece>> {
    private final Map<String, Class<? extends AbstractPiece>> pieceClass;

    public ClassConverterToClass() {
        this.pieceClass = Stream.of(King.class, Queen.class, Bishop.class, Knight.class, Rook.class, Pawn.class)
                .collect(Collectors.toMap(Class::getSimpleName, c -> c));
    }

    @Override
    public Class<? extends AbstractPiece> convert(String source) {
        return pieceClass.get(source);
    }
}
