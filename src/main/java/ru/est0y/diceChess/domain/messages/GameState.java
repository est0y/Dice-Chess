package ru.est0y.diceChess.domain.messages;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.est0y.diceChess.domain.Move;

import java.util.List;

@RequiredArgsConstructor
@Data
public class GameState {
    private final GameRole turnHolder;

    private final Move move;

    private final List<Piece> pieces;
}
