package ru.est0y.diceChess.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor
@Data
@Document("users")
public class User implements UserDetails {

    private String name;

    private String password;

    private Set<GrantedAuthority> grantedAuthorities;

    private boolean isPlaying;

    @AllArgsConstructor
    public static class GrantedAuthorityImpl implements GrantedAuthority {
        private final String authority;

        @Override
        public String getAuthority() {
            return authority;
        }
    }


    public User(String name, String password,boolean isPlaying, String... authorities) {
        this.name = name;
        this.password = password;
        this.grantedAuthorities = Arrays.stream(authorities).map(GrantedAuthorityImpl::new).collect(Collectors.toSet());
        this.isPlaying = isPlaying;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
