package ru.est0y.diceChess.domain.messages;

import lombok.Data;

import java.util.List;

@Data
public class ConnectMessage {
    private final GameRole role;

    private final GameRole turnHolder;

    private final String fen;

    private final List<Piece>pieces;
}
