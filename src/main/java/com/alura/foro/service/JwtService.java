package com.alura.foro.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        // Convertir la clave secreta en un array de bytes y generar una clave segura
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Asignar el nombre de usuario como sujeto del token
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .expiration(new Date(System.currentTimeMillis() + expiration)) // Fecha de expiración
                .signWith(getSigningKey(), Jwts.SIG.HS512) // Firmar el token con la clave secreta
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey()) // Verificar el token con la clave secreta
                    .build()
                    .parseSignedClaims(token); // Parsear el token
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey()) // Verificar el token con la clave secreta
                .build()
                .parseSignedClaims(token) // Parsear el token
                .getPayload(); // Obtener los datos del token
        return claims.getSubject(); // Obtener el nombre de usuario
    }
}