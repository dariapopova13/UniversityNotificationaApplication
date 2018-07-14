package com.university.itis.authentication;//package com.todoist.sql.server.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.itis.dto.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


@Component
public class RestAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper objectMapper;

    public RestAuthenticationProcessingFilter(String url, HttpMethod method) {
        super(new AntPathRequestMatcher(url, method.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            UserFormDto userFormDto = objectMapper.readValue(request.getReader(), UserFormDto.class);
            if (userFormDto == null || userFormDto.getPassword() == null || userFormDto.getEmail() == null)
                throw new AuthenticationCredentialsNotFoundException("Bad credentials");
            String email = userFormDto.getEmail();
            String password = userFormDto.getPassword();

            email = email.trim();
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

}
