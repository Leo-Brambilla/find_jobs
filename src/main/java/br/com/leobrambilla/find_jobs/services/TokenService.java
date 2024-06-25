package br.com.leobrambilla.find_jobs.services;


import br.com.leobrambilla.find_jobs.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public String tokenGenerator(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Find Jobs")
                    .withSubject(user.getUsername())
                    .withClaim("role", getRoleAsString(user))
                    .withExpiresAt(expiresDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            logger.error("Error getting JWT token", e);
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }
    private String getRoleAsString(User user) {
        return String.join(",", user.getRoles());
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Find Jobs")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            logger.error("JWT token verification failed", e);
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant expiresDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
