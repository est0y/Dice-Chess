package ru.est0y.diceChess.services;

import ru.est0y.diceChess.domain.Game;

import java.util.Optional;

public interface GameService {
    Optional<Game> findById(String id);

    Optional<Game>findByIdAndTurnHolderId(String id, String turnHolderId);

    void save(Game game);
}
