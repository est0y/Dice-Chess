package ru.est0y.diceChess;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.est0y.diceChess.domain.board.BoardSize;

@SpringBootApplication
@Slf4j
@EnableMongock
public class App {
    public static void main(String[] args) {
        var app = SpringApplication.run(App.class);
        app.getBean(GameProducer.class).createGames();
    }

    @Bean
    BoardSize boardSize() {
        return new BoardSize(8, 8);
    }
}