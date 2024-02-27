package ru.est0y.diceChess;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@EnableMongock
public class App {
    /*
     white: user1  password:1
     black: user2 password:1
     url: http://localhost:8080/room/1
     */
    public static void main(String[] args) {
        var app = SpringApplication.run(App.class);
        app.getBean(GameProducer.class).createGames();
    }

}