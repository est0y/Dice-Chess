package ru.est0y.diceChess.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.est0y.diceChess.domain.Move;
import ru.est0y.diceChess.services.GameService;
import ru.est0y.diceChess.services.messaging.GameMessagingService;
import ru.est0y.diceChess.services.movingPipeline.MovePipeline;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MoveController {

    private final GameService gameService;

    private final MovePipeline movePipeline;

    private final GameMessagingService gameMessagingService;

    @MessageMapping("/game/id.{gameId}/move")
    void simpleMove(Principal principal, @DestinationVariable String gameId, @Valid Move move) {
        var game = gameService.findByIdAndTurnHolderId(gameId, principal.getName()).orElseThrow();
        movePipeline.run(move,game);
    }

    @MessageMapping("connect/{gameId}")
    void getConnectMessage(Principal principal, @DestinationVariable String gameId) {
        var game = gameService.findById(gameId).orElseThrow();
        gameMessagingService.sendToUser(game,principal);
    }
}
