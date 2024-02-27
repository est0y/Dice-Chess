package ru.est0y.diceChess.services.directions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.est0y.diceChess.config.props.GameProps;
import ru.est0y.diceChess.services.directions.ChessBoardNavigator;
import ru.est0y.diceChess.services.directions.MoveMaker;
import ru.est0y.diceChess.services.directions.MoveMakerImpl;
import ru.est0y.diceChess.services.squares.SquareCacheImpl;

@Configuration
@Import(MoveMakerImpl.class)
public class Config {
    @Bean
    public GameProps gameProps() {
        return new GameProps(8, 8);
    }
}
