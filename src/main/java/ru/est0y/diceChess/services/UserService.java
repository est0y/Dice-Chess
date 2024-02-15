package ru.est0y.diceChess.services;

import ru.est0y.diceChess.domain.security.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String name);
}
