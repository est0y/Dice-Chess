package ru.est0y.diceChess.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.est0y.diceChess.domain.security.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
}
