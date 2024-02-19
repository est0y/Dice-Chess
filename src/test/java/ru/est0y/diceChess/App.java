package ru.est0y.diceChess;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import ru.est0y.diceChess.config.mongo.MongoConfig;
import ru.est0y.diceChess.config.props.GameProps;

/*@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})*/
@SpringBootConfiguration
@ComponentScan(basePackages = {"ru.est0y.diceChess.services"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MongoConfig.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ru.est0y.diceChess.services.data.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ru.est0y.diceChess.repositories.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ru.est0y.diceChess.services.movingPipeline.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ru.est0y.diceChess.services.messaging.*")
        }
)
public class App {
    @TestConfiguration
    static class Config {
        @Bean
        GameProps gameProps() {
            return new GameProps(8, 8);
        }
    }
}
