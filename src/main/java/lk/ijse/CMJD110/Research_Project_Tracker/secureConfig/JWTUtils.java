package lk.ijse.CMJD110.Research_Project_Tracker.secureConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Configuration
public class JWTUtils {

    @Value("${signature}")
    private String signature;

    private Key key() {
        // ✅ Treat key as UTF-8 text (works for plain/hex strings)
        return Keys.hmacShaKeyFor(signature.getBytes(StandardCharsets.UTF_8));
    }

    // ✅ Generate JWT
    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Validate token properly
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token); // ✅ correct method
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // ✅ Extract username from token
    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
