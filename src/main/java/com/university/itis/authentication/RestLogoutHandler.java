package com.university.itis.authentication;//package com.todoist.sql.server.authentication;


import com.university.itis.model.Token;
import com.university.itis.repository.TokenRepository;
import com.university.itis.service.UserService;
import com.university.itis.service.impl.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class RestLogoutHandler extends SecurityContextLogoutHandler {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = (String) tokenAuthenticationService.getAuthentication((HttpServletRequest) request).getPrincipal();
        Token token = tokenRepository.findByEmailAndEndDateIsNull(username);
        if (token != null) {
            token.setEndDate(new Date());
            tokenRepository.save(token);
        }
        super.logout(request, response, authentication);
    }


}
