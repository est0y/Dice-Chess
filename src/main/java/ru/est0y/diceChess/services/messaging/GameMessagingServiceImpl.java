package ru.est0y.diceChess.services.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.messages.GameRole;
import ru.est0y.diceChess.domain.messages.GameState;
import ru.est0y.diceChess.domain.messages.PieceMapping;
import ru.est0y.diceChess.services.fen.FenUtils;
import ru.est0y.diceChess.domain.Game;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.domain.messages.ConnectMessage;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class GameMessagingServiceImpl implements GameMessagingService {

    private final PieceMapping pieceMapping;

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final FenUtils fenUtils;


    @Override
    public void sendToAll(Move move, Game game) {
        var role = game.getTurnHolder().getTeamNumber() == 1 ? GameRole.WHITE : GameRole.BLACK;
        var dice = game.getLegalPieces().stream().map(pieceMapping::getPiece).toList();
        simpMessagingTemplate.convertAndSend(String.format("/game/id.%s/move",game.getId()),
                new GameState(role, move, dice));
    }

    @Override
    public void sendToUser(Game game, Principal principal) {
        var playerOptional = game.getPlayers().stream().filter(p -> p.getId().equals(principal.getName()))
                .findFirst();
        var turnHolderColor = game.getTurnHolder().getTeamNumber() == 1 ? GameRole.WHITE : GameRole.BLACK;
        var fen = fenUtils.fenFromGame(game);
        ConnectMessage message;
        GameRole role;
        role = playerOptional.map(player -> player.getTeamNumber() == 1 ? GameRole.WHITE : GameRole.BLACK)
                .orElse(GameRole.SPECTATOR);
        var dice = game.getLegalPieces().stream().map(pieceMapping::getPiece).toList();
        message = new ConnectMessage(role, turnHolderColor, fen, dice);
        simpMessagingTemplate.convertAndSendToUser(principal.getName(),
                String.format("/queue/role/%s",game.getId()),
                message);
    }
}
