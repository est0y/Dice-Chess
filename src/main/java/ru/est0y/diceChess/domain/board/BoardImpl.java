package ru.est0y.diceChess.domain.board;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.est0y.diceChess.domain.AbstractPiece;
import ru.est0y.diceChess.domain.Square;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class BoardImpl implements Board {
    private final Map<Square, AbstractPiece> pieces;

    private final BoardSize boardSize;

    @Override
    public Optional<AbstractPiece> getPiece(Square square) {
        return Optional.ofNullable(pieces.get(square));
    }

    @Override
    public void putPiece(Square square, AbstractPiece abstractPiece) {
        pieces.put(square, abstractPiece);
    }

    @Override
    public void remove(Square square) {
        pieces.remove(square);
    }

    @Override
    public BoardSize getSize() {
        return boardSize;
    }

    @Override
    public Board clone() {
        return new BoardImpl(new HashMap<>(pieces),boardSize);
    }

    @Override
    public String toString() {
        return pieces.keySet().stream()
                .collect(Collectors.toMap(
                        (k) -> k,
                        (k) -> pieces.get(k).getClass().getSimpleName() + " " + pieces.get(k).getTeam())).toString();
    }
}
