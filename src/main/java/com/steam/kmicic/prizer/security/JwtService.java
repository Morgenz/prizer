package com.steam.kmicic.prizer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtService {


    public static final int tokenExpirationTime = 30 * 60 * 1000;
    public static final String TOKEN_KEY = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/register";

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .sign(Algorithm.HMAC512(TOKEN_KEY.getBytes()));
    }

    public static String parseToken(String token)  {
        return JWT.require(Algorithm.HMAC512(TOKEN_KEY.getBytes())).build().verify(token.replace(TOKEN_PREFIX,"")).getSubject();
    }

//    public static Claims getClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(TOKEN_KEY)
//                .parseClaimsJws(token.substring(7))
//                .getBody();
//    }
//
//    public static String updateExpirationDateToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        return Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
//                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
//                .compact();
//    }
}