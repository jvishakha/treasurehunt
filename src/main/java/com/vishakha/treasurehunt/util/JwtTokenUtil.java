package com.vishakha.treasurehunt.util;

import com.vishakha.treasurehunt.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
