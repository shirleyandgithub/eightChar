package com.site.blog.my.core.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token != null ) {
            try {
                // 验证 token 的逻辑
                if (validateToken(token)) {
                    // 设置用户信息到 SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
                     Long userId = getUserIdFromToken(token);
                     request.setAttribute("userId", userId);
                }
            } catch (Exception e) {
                // 处理 token 验证失败的情况
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

     private Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

         return claims.get("userId", Long.class);
     }

     private boolean validateToken(String token) {
        try {
            // 解析 token 并验证签名
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            // 检查 token 是否过期
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                return false;
            }

            // 其他自定义验证逻辑（可选）
            // 例如，检查 token 是否被撤销等

            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            // 处理各种异常情况
            return false;
        }
    }

     private Authentication getAuthentication(String token) {
        // 从 token 中提取用户信息并创建 Authentication 对象
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
    }
}
