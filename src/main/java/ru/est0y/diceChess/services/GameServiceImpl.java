package ru.est0y.diceChess.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.repositories.GameRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public Optional<Game> findById(String id) {
        return gameRepository.findById(id);
    }

    @Override
    public Optional<Game> findByIdAndTurnHolderId(String id, String turnHolderId) {

        return gameRepository.findByIdAndTurnHolderId(id, turnHolderId);
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }
}
