package com.site.blog.my.core.config;

import com.site.blog.my.core.controller.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UserVO userVO) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userVO);
    }

    private String createToken(Map<String, Object> claims, UserVO userVO) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userVO.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("userId", userVO.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = getAllClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}
