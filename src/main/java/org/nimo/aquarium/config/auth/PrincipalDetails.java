package org.nimo.aquarium.config.auth;

import lombok.Getter;
import lombok.Setter;
import org.nimo.aquarium.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PrincipalDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public PrincipalDetails(User user){ this.user = user; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole();});

        return collector;
    }

    @Override
    public String getPassword() { return user.getPassword(); }

    @Override
    public String getUsername() { return user.getUsername(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
