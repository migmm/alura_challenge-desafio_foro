package com.alura.foro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private TokenService tokenService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilitar CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesiones
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll() // Permitir acceso a /auth sin autenticación
                .requestMatchers(HttpMethod.GET, "/topics").permitAll() // Permitir acceso público a GET /topics
                .anyRequest().authenticated() // El resto de los endpoints requieren autenticación
                .and()
                .addFilterBefore(new JwtTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class); // Filtro JWT

        return http.build();
    }

    // Exponer el AuthenticationManager como un bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}