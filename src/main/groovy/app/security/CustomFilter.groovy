package app.security

import groovy.util.logging.Slf4j
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * Created by tolkv on 1/13/2015.
 */
@Slf4j
class CustomFilter extends GenericFilterBean {
    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug '=' * 40
        log.debug 'Custom filter invoked'
        log.debug '=' * 40

        chain.doFilter(request, response)
    }
}
