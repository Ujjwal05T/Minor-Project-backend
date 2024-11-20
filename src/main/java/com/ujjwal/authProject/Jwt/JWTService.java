package com.ujjwal.authProject.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTService {

    private String secretkey = "";
    // private final String secretkey = "ce644201fbf17a50cff449be4dc26f31a4a13f6958a4faa89ced36354644118e79254ccbc920103f4d2ed3426112dfa20b8c58b909497bee4a98419c5932b2a21b0634703cbe8d1b1a666fdbd778094928f3c27c508e5e6054fec04fd4cdcb9eed8f8915740440f89a651d95b2f5ed63640c9274d09cbbaab3a33605cc6c5eb123074ecef64bd959f222b47b6af728cedb7ba8b9c68301b948d6fbb29d92672de62e5e521cdf746e2b5e22f0aadd4f9bb5313fe28c394516c45a49263ded0356728ba584b2f6ce53f7e41d5f3f51473bac9d5c9eaa57d688cdca4985e386d1c7c419093c6e47cbbe98d8be7e521048d7689af75259c78b9b65d81fbe6265edd4";

//    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretkey=Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        }


    public String generateToken(String username) {

        Map <String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
