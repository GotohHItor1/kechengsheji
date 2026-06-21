package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JwtUtils {

    /** 签名密钥 */
    private static final Key SIGN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /** 令牌过期时间: 12 小时 */
    private static final long EXPIRE = 1000L * 60 * 60 * 12;

    /**
     * 生成 JWT 令牌
     * @param claims 自定义载荷
     * @return JWT 令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SIGN_KEY)
                .compact();
    }

    /**
     * 解析 JWT 令牌
     * @param jwt JWT 令牌字符串
     * @return Claims 对象
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGN_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
