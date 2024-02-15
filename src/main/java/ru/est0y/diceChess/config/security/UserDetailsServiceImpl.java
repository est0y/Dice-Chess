package ru.est0y.diceChess.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.est0y.diceChess.services.UserService;


@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
