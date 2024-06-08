package com.example.cw_spring.service.impl;

import com.example.cw_spring.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Override
    public String extractUsername(String token) {
        log.info(extractClaim(token, Claims::getSubject));
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        log.info(userDetails.toString());
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean istokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        log.info(username);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private String generateToken(Map<String,Object> extractClaims, UserDetails userDetails){
        extractClaims.put("role",userDetails.getAuthorities());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000*60*60*24);
        Date refreshTokenExpire = new Date(now.getTime() + 1000*60*60*24);


        String accessToken = Jwts.builder().setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

        String refreshToken = Jwts.builder().setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpire)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

        log.info(accessToken+":"+refreshToken);

        return accessToken + " : " +refreshToken;
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResole) {
        final Claims claims = getAllClaims(token);
        log.info(claims.toString());
        return claimsResole.apply(claims);
    }

    private Claims getAllClaims(String token) {
//        log.info(Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody().toString());
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
