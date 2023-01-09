package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.AccessTokenDecoder;
import com.boxvent.boxventwebsite.business.AccessTokenEncoder;
import com.boxvent.boxventwebsite.business.exception.InvalidAccessTokenException;
import com.boxvent.boxventwebsite.domain.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(accessToken.getRoles())) {
            claimsMap.put("roles", accessToken.getRoles());
        }
        Instant now = Instant.now();
        Date expiration= Date.from(now.plus(30, ChronoUnit.MINUTES));
        System.out.println(expiration);
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(expiration)
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(accessTokenEncoded);
            Claims claims = (Claims) jwt.getBody();

            List<String> roles = claims.get("roles", List.class);

            return AccessToken.builder()
                    .subject(claims.getSubject())
                    .roles(roles)
                    .build();
        } catch (JwtException e) {
            // Create a new JWT token with the subject "guest" and the role "GUEST"
            String guestToken = Jwts.builder()
                    .setSubject("guest")
                    .claim("roles", Collections.singletonList("GUEST"))
                    .signWith(key)
                    .compact();

            // Return the guest token as an AccessToken object
            return AccessToken.builder()
                    .subject("guest")
                    .roles(Collections.singletonList("GUEST"))
                    .build();
        }
    }

}
