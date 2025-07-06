package com.decrux.zenspent.config.security;

import com.decrux.zenspent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.decrux.zenspent.config.security.Constants.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SpaCsrfTokenRequestHandler spaCsrfTokenRequestHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final UserRepository userRepository;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(this.spaCsrfTokenRequestHandler))
                .sessionManagement(session -> session.maximumSessions(1))
                .formLogin(form -> form
                        .loginPage(FRONTEND_LOGIN_URL.getValue()).permitAll()
                        .loginProcessingUrl(BACKEND_LOGIN_PROCESSING_URL.getValue()).permitAll()
                        .successHandler(this.customAuthenticationSuccessHandler)
                        .failureHandler(this.customAuthenticationFailureHandler))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(FRONTEND_STATIC_ASSETS.getValue()).permitAll()
                        .requestMatchers(FRONTEND_FAVICON.getValue()).permitAll()
                        .requestMatchers(FRONTEND_LANDING_PAGE.getValue()).permitAll()
                        .requestMatchers(FRONTEND_REGISTER_URL.getValue()).permitAll()
                        .requestMatchers(BACKEND_REGISTER_ENDPOINT.getValue()).permitAll()
                        // TODO set GET_CSRF_TOKEN_ENDPOINT only in development mode
                        // TODO to avoid CSRF token exposure in production
                        .requestMatchers(GET_CSRF_TOKEN_ENDPOINT.getValue()).permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(new CustomUserDetailsService(this.userRepository))
                .logout(logout -> logout
                        .logoutUrl(BACKEND_LOGOUT_PROCESSING_URL.getValue())
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(this.customLogoutSuccessHandler))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
