package ru.est0y.diceChess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.services.GameService;

@Service
@RequiredArgsConstructor
public class GameProducer {

    private final GameCreator gameCreator;

    private final GameService gameService;

    public void createGames() {
        var game = gameCreator.createGame("1", "user1", "user2",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        gameService.save(game);
    }
}
