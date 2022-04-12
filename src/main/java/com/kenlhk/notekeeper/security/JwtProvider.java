package com.kenlhk.notekeeper.security;

import com.kenlhk.notekeeper.domain.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    @Value("${app.jwt.header}")
    private String authorizationHeader;

    private final UserDetailsService userDetailsService;

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer("NoteKeeper")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader(authorizationHeader);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new JwtAuthenticationException("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            throw new JwtAuthenticationException("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("JWT token is expired.");
        } catch (UnsupportedJwtException e) {
            throw new JwtAuthenticationException("JWT token is unsupported.");
        } catch (IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT claims string is empty.");
        }
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token){
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}

