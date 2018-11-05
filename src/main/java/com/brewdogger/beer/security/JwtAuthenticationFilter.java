package com.brewdogger.beer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.brewdogger.beer.entity.BrewdoggerUser;
import com.brewdogger.beer.exception.BrewdoggerAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.brewdogger.beer.security.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            BrewdoggerUser credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), BrewdoggerUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword().concat(SALT),
                            new ArrayList<>())
                    );
        } catch (IOException e) {
            logger.info("JwtAuthenticationFilter::attemptAuthentication - Could not authenticate request");
            throw new BrewdoggerAuthenticationException("Could not authenticate request");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
