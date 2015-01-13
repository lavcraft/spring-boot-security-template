package app.security.authentication;

import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by tolkv on 12/28/2014.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    Logger log = getLogger(CustomAuthenticationProvider.class);

    boolean authTrigger = true;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("Attempt to authenticate {}", authentication);

        if (authTrigger) {
            authTrigger = false;
            authentication.setAuthenticated(true);
        } else {
            authentication.setAuthenticated(false);
            authTrigger=true;
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.debug("Authentication {} supported?", authentication);
        return CustomAuthentication.class.isAssignableFrom(authentication);
    }
}
