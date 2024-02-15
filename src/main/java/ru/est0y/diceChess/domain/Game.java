package ru.est0y.diceChess.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.est0y.diceChess.domain.board.DiceChessBoard;

import java.util.List;

@Data
@Document
@AllArgsConstructor
public class Game {
    private final String id;

    private final List<Player> players;

    private Player turnHolder;

    private List<Class<? extends AbstractPiece>> legalPieces;

    private final DiceChessBoard board;


}
