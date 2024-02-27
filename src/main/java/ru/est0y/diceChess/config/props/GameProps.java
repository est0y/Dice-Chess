package ru.est0y.diceChess.config.props;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import ru.est0y.diceChess.domain.board.BoardSize;

@ConfigurationProperties("game.board-size")
@Getter
public class GameProps {
    private final BoardSize boardSize;

    @ConstructorBinding
    public GameProps(int x, int y) {
        this.boardSize = new BoardSize(x, y);
    }

}
