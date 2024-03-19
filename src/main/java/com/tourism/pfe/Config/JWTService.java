package com.tourism.pfe.Config;

import com.tourism.pfe.User.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "GRwuJqkb2tjRv+hOEdfy2pujvKcna5RnSQ6K+eJUi5V6egI2iuyZ4ZjNLQchVgud";

    private boolean isTokenValid;
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }


    public boolean isTokenValid(String token,UserDetails user){
        final String userName = extractUsername(token);
        return (userName.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExperation(token).before(new Date());
    }

    private Date extractExperation(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails user) {
        Map hash = new HashMap<>();
        if(user instanceof User){
            User user1 = (User) user;
            hash.put("name",user1.getName());
            hash.put("userId",String.valueOf(user1.getId()));
            hash.put("userRole",(user1.getRole().name()));
        }
        return generateToken(hash,user) ;
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails user){
        User user1 = (User) user;
        extraClaims.put("userId",user1.getId());
        extraClaims.put("userRole",user1.getRole().name());
        extraClaims.put("name",user1.getName());


        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaimes(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T>  T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaimes(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", String.class));
    }

    public String extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("userRole", String.class));
    }

    public String extractName(String token) {
        return extractClaim(token, claims -> claims.get("name", String.class));
    }
}