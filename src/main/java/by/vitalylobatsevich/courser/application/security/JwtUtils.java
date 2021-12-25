package by.vitalylobatsevich.courser.application.security;

import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${courser.security.jwt.secret}")
    private String jwtSecret;

    @Value("${courser.security.jwt.lifetime}")
    private Integer jwtLifetime;

    public String generate(final Authentication authentication) {
        return Jwts.builder()
                .setSubject(((UserDetailsImpl) authentication.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtLifetime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(final String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validate(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (final SignatureException exception) {
            log.error("Invalid JWT signature: {}", exception.getMessage());
        } catch (final MalformedJwtException exception) {
            log.error("Invalid JWT token: {}", exception.getMessage());
        } catch (final ExpiredJwtException exception) {
            log.error("JWT token is expired: {}", exception.getMessage());
        } catch (final UnsupportedJwtException exception) {
            log.error("JWT token is unsupported: {}", exception.getMessage());
        } catch (final IllegalArgumentException exception) {
            log.error("JWT claims string is empty: {}", exception.getMessage());
        }
        return false;
    }

}
