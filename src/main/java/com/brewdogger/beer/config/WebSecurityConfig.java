package com.brewdogger.beer.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import com.brewdogger.beer.security.JwtAuthenticationFilter;
import com.brewdogger.beer.security.JwtAuthorizationFilter;
import com.brewdogger.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth0.apiAudience}")
    private String apiAudience;

    @Value("${auth0.issuer}")
    private String issuer;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login")
                        .permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "api/user")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/beer")
                        .authenticated()
                    .antMatchers(HttpMethod.GET, "/api/beer/**")
                        .authenticated()
                    .antMatchers(HttpMethod.POST, "/api/beer")
                        .authenticated()
                    .antMatchers(HttpMethod.PUT, "/api/beer/**")
                        .authenticated()
                    .antMatchers(HttpMethod.DELETE, "/api/beer/**")
                        .authenticated()
                    .antMatchers(HttpMethod.GET, "/api/brewery")
                        .authenticated()
                    .antMatchers(HttpMethod.GET, "/api/brewery/**")
                        .authenticated()
                    .antMatchers(HttpMethod.POST, "/api/brewery")
                        .authenticated()
                    .antMatchers(HttpMethod.PUT, "/api/brewery/**")
                        .authenticated()
                    .antMatchers(HttpMethod.DELETE, "/brewery/**")
                        .authenticated()
                    .antMatchers(HttpMethod.GET,"/h2-console/**")
                        .permitAll()
                    .anyRequest()
                        .permitAll()
            .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                    .cors()
                .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                        .anyRequest()
                        .permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }
}