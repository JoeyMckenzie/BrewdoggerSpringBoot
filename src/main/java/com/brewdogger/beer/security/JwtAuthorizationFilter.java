package com.brewdogger.beer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.brewdogger.beer.security.SecurityConstants.HEADER_STRING;
import static com.brewdogger.beer.security.SecurityConstants.SECRET;
import static com.brewdogger.beer.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request.getHeader(HEADER_STRING) != null) {
            var header = request.getHeader(HEADER_STRING);

            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }

            var authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(HEADER_STRING);

        if (token != null) {
            var user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

            return null;
        }

        return null;
    }


}
