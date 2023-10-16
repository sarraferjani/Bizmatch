package tn.bizmatch.userManagement.SConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String secret_key="9bdd0c004e537a8f9c0109eb3d3f75e812e129c73209a5b7f89c6f7383b8f016";
    public String extractUsername(String jwt) {

        return extractClaim(jwt,Claims::getSubject);
    }

    public<T> T  extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
     byte[] keyBytes= Decoders.BASE64.decode(secret_key);
     return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }


    public String generateToken(Map<String,Object> exrtaclaims, UserDetails userDetails)
    {
            return Jwts
                    .builder()
                    .setClaims(exrtaclaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                    .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                    .compact();
    }

    public  boolean isTokenValid(String jwt, UserDetails userDetails){
        final String username=extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);

    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt,Claims::getExpiration);
    }
}
