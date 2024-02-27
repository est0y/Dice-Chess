package ru.est0y.diceChess.services.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.diceChess.domain.security.User;
import ru.est0y.diceChess.repositories.UserRepository;
import ru.est0y.diceChess.services.data.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
