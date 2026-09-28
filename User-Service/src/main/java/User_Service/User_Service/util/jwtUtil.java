package User_Service.User_Service.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class jwtUtil {

    private final Key key;

    private final Long jwtExpirationMs;

    public jwtUtil(@Value("${app.jwt.secret}") String Secret ,
                   @Value("${app.jwt.expiration-ms}") Long jwtExpirationMs){

        this.key = Keys.hmacShaKeyFor(Secret.getBytes());
        this.jwtExpirationMs = jwtExpirationMs;

    }

    public String generateToken(Long userId,String email,String role){
       long now = System.currentTimeMillis();
      return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email",email)
                .claim("role",role)
                .issuedAt(new Date(now))
                .expiration(new Date(jwtExpirationMs))
                .signWith(key)
                .compact();
    }
}
