package ru.est0y.diceChess.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.est0y.diceChess.domain.Game;

import java.util.Optional;

public interface GameRepository extends MongoRepository<Game, String> {
    Optional<Game> findByIdAndTurnHolderId(String id, String turnHolderId);
}
