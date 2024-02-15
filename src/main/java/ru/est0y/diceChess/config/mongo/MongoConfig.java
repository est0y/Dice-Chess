package ru.est0y.diceChess.config.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.est0y.diceChess.config.mongo.converters.ClassConverterToString;
import ru.est0y.diceChess.config.mongo.converters.ClassConverterToClass;
import ru.est0y.diceChess.config.mongo.converters.SquareToStringConverter;
import ru.est0y.diceChess.config.mongo.converters.StringToSquareConverter;

@Configuration
@RequiredArgsConstructor
@EnableMongoRepositories(basePackages = "ru.est0y.diceChess.repositories")
public class MongoConfig extends AbstractMongoClientConfiguration {
    private final StringToSquareConverter stringToSquareConverter;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${mongoDb.databaseName}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }

    @Override
    protected void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter(new SquareToStringConverter());
        adapter.registerConverter(stringToSquareConverter);
        adapter.registerConverter(new ClassConverterToClass());
        adapter.registerConverter(new ClassConverterToString());
    }
}