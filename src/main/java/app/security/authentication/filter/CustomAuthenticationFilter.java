package app.security.authentication.filter;

import app.security.authentication.CustomAuthentication;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by tolkv on 12/28/2014.
 */
public class CustomAuthenticationFilter extends GenericFilterBean {

    Logger log = getLogger(CustomAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Do Customr Filter: Req: {}, Res: {}", request, response);
        HttpServletRequest httpRequest = asHttp(request);
        HttpServletResponse httpResponse = asHttp(response);
        Optional<String> authHeader = Optional.ofNullable(httpRequest.getHeader("TEST"));
        if (authHeader.isPresent()) {
            log.debug("Found header: {}",authHeader.get());
            CustomAuthentication customAuthentication = new CustomAuthentication(authHeader.get());

            Authentication authenticate = authenticationManager.authenticate(customAuthentication);
            if(authenticate.isAuthenticated()) {
                httpResponse.setStatus(201);
                chain.doFilter(request, response);
                return;
            }
        } else {
            log.debug("Athentication header not found");
            return;
        }
        return;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

}
