package com.dismuss.bookstore.service;

import com.dismuss.bookstore.common.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtService {
    private final static long EXPIRATIONTIME = 864000000;
    private final static Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(KEY)
                .compact();
    }

    public Optional<String> getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String username = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token.replace(Constants.Authorization.BEARER_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (username != null) {
                return Optional.of(username);
            }
        }
        return Optional.empty();
    }
}
