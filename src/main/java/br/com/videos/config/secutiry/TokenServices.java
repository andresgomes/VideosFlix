package br.com.videos.config.secutiry;

import br.com.videos.modelo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServices {

    @Value("${video.jwt.expiration}")
    private String expiration;

    @Value("${video.jwt.expiration}")
    private String secret;
    

    public String tokenGenerate(Authentication authentication) {

        User conected = (User) authentication.getPrincipal();
        Date criationDate = new Date();
        Date expirationDate = new Date(criationDate.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API de Videos")
                .setSubject(conected.getId().toString())
                .setIssuedAt(criationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
