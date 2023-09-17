package com.example.class_studen.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "@asdfgfgf6t7yg2huhqwer12345ghj@";

    public String generateToken(String user) {
        return Jwts
                .builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public boolean isValid(String token) {
        if (!parser().isSigned(token)) return false;
        try {
            Claims body = parser()
                    .parseClaimsJws(token)
                    .getBody();
            return !StringUtils.isBlank(body.getSubject());
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T getClaims(String token, String claimName, Class<T> type) {
        try {
            return parser()
                    .parseClaimsJws(token)
                    .getBody()
                    .get(claimName, type);
        } catch (Exception e) {
            return null;
        }
    }

    private JwtParser parser() {
        return Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build();
    }
}
