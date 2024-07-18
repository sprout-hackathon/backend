package com.hackathon.sprout.global.jwt;

import com.hackathon.sprout.global.jwt.exception.ExpiredTokenException;
import com.hackathon.sprout.global.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtProvider {
    @Value("${jwt.access_secret}")
    private String accessSecret;

    @Value("${jwt.refresh_secret}")
    private String refreshSecret;

    public Claims parseClaims(String token, boolean isRefreshToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(isRefreshToken ? refreshSecret : accessSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(e);
        }
    }

    public void validateToken(String token, boolean isRefreshToken) {
        try {
            parseClaims(token, isRefreshToken);
        } catch (SignatureException | UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e) {
            throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token, false);
        String userId = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
    }
}
