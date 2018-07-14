package com.university.itis.authentication;//package com.todoist.sql.server.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.itis.dto.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Daria Popova on 10.05.17.
 */
public class RestUserNameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private ObjectMapper objectMapper;
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "email";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String emailParameter = "email";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            UserFormDto userFormDto = null;
            try {
                userFormDto = objectMapper.readValue(request.getReader(), UserFormDto.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (userFormDto == null || userFormDto.getPassword() == null || userFormDto.getEmail() == null)
                throw new AuthenticationCredentialsNotFoundException("Bad credentials");
            String email = userFormDto.getEmail();
            String password = userFormDto.getPassword();

            email = email.trim();
            UsernamePasswordAuthenticationToken authRequest
                    = new UsernamePasswordAuthenticationToken(email, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

}
