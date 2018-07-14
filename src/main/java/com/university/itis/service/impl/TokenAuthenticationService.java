package com.university.itis.service.impl;//package com.todoist.sql.server.service;




import com.university.itis.dto.UserFormDto;
import com.university.itis.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * Created by Daria Popova on 12.05.17.
 */
@Service
public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 864_000_000;
    private static final String SECRET = "DoctorWho";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";
    @Autowired
    private TokenRepository tokenRepository;

    public void addAuthentication(UserFormDto userFormDto) {
        String jwt = Jwts.builder()
                .setSubject(userFormDto.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        userFormDto.setToken(TOKEN_PREFIX + " " + jwt);
    }

    public void addAuthentication(HttpServletResponse res, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return (user != null && tokenRepository.findByEmailAndTokenAndEndDateIsNull(user, token) != null)
                    ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        return null;
    }
}