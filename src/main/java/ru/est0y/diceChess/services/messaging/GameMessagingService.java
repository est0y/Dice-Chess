package ru.est0y.diceChess.services.messaging;

import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;

import java.security.Principal;

public interface GameMessagingService {

    void sendToAll(Move move, Game game);

    void sendToUser(Game game, Principal user);
}
