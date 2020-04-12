package com.akn.springbootfirstproject.utils;

import com.akn.springbootfirstproject.model.ApplicationUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.akn.springbootfirstproject.constants.SecurityConstants.*;

@Component
public class TokenUtils {

    private final String roleName = "role";

    public String createAccessToken(ApplicationUser user){
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim(roleName, user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        return token;
    }

    public ApplicationUser verifyAccessToken(String token){
        DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""));
        ApplicationUser user  = new ApplicationUser();
        user.setUsername(decodedJwt.getSubject());
        user.setRole(decodedJwt.getClaim(roleName).asString());
        return user;
    }

    public String createRefreshToken(ApplicationUser user){
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withClaim(roleName, user.getAuthorities().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(REFRESH_SECRET.getBytes()));
        return refreshToken;
    }

    public String verifyRefreshToken(String token){
        String username = JWT.require(Algorithm.HMAC512(REFRESH_SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        return username;
    }
}
