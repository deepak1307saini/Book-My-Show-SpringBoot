package com.example.BookMyShowSpringBootApplication.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String SECRET_KEY = "4E645266556A586E3272357538782F413F4428472B4B6250655368566B597033";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public String extractUsername(String token) throws RuntimeException {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) throws RuntimeException {
        final Claims claims=extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }


    public  String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24*60))
                .compact();
    }


    public boolean isTokenValid(String token,UserDetails userDetails) {
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) &&!isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) throws RuntimeException {
       try{ return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();}
       catch (SignatureException ex) {
           throw new RuntimeException(ex.getMessage());
       } catch (MalformedJwtException ex) {
           throw new RuntimeException(ex.getMessage());
       } catch (ExpiredJwtException ex) {
           throw new RuntimeException(ex.getMessage());
       } catch (UnsupportedJwtException ex) {
           throw new RuntimeException(ex.getMessage());
       } catch (IllegalArgumentException ex) {
           throw new RuntimeException(ex.getMessage());
       }
       catch (RuntimeException ex){
           throw new RuntimeException(ex.getMessage());
       }

    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}