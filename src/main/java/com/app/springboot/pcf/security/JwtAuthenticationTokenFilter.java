package com.app.springboot.pcf.security;

import com.app.springboot.pcf.security.dto.JwtAuthenticationToken;
import com.app.springboot.pcf.security.exception.JwtTokenMissingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anish Panthi
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    private static final List<RequestMatcher> PATHS = Arrays.asList(
            new AntPathRequestMatcher("/api/v0.1/users/**"),
            new AntPathRequestMatcher("/api/v0.1/messages-internal/**")
    );

    public JwtAuthenticationTokenFilter() {
        super(new OrRequestMatcher(PATHS));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String header = request.getHeader(this.tokenHeader);

        String authToken;

        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.substring(7);
        } else {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }
}
