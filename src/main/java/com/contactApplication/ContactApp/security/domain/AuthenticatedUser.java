package com.contactApplication.ContactApp.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class AuthenticatedUser extends User {
    private String login;
    private String id;


    public AuthenticatedUser(
            String login, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            String id
    ) {
        super(login, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.login = login;
        this.id = id;
    }

}
