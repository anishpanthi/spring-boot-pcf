package com.app.springboot.pcf.security.util;

import com.app.springboot.pcf.security.dto.JwtUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Anish Panthi
 */
@Component
@Slf4j
public class JwtTokenValidator {

    @Value("${jwt.secret}")
    private String secret;

    public JwtUserDto parseToken(String token) {

        JwtUserDto u = null;

        try {
            Claims body = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
            String userId = (String) body.get("userId");
            String subject = body.getSubject();
            String role = (String) body.get("role");
            u = new JwtUserDto(Long.parseLong(userId), subject, role);
            log.info("Logged in User:: {}", u.toString());
        } catch (JwtException e) {
            log.error("", e);
        }

        return u;
    }
}
