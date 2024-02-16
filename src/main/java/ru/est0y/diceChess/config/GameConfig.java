package ru.est0y.diceChess.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.est0y.diceChess.config.props.GameProps;

@Configuration
@EnableConfigurationProperties(GameProps.class)
public class GameConfig {
}
