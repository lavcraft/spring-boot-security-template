package app.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by tolkv on 12/28/2014.
 */
public class CustomAuthentication implements Authentication {
    private String principal;
    private boolean authenticated;

    public CustomAuthentication(String principal) {
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>(1) {
            {
                add(new SimpleGrantedAuthority(principal));
            }
        };
    }

    @Override
    public Object getCredentials() {
        return principal;
    }

    @Override
    public Object getDetails() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return "my name is " + principal;
    }

    @Override
    public String toString() {
        return "CustomAuthentication{" +
                "principal='" + principal + '\'' +
                ", authenticated=" + authenticated +
                '}';
    }
}
