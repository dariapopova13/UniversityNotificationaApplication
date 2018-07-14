package com.university.itis.service.impl;

import com.university.itis.dto.UserDto;
import com.university.itis.dto.UserFormDto;
import com.university.itis.model.Token;
import com.university.itis.model.User;
import com.university.itis.model.builder.TokenBuilder;
import com.university.itis.repository.TokenRepository;
import com.university.itis.repository.UserRepository;
import com.university.itis.service.MailService;
import com.university.itis.service.UserService;
import com.university.itis.utils.DtoUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties")
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenAuthenticationService tokenService;
    @Autowired
    private DtoUtils dtoUtils;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto get(Long id) {
        User user = userRepository.findOne(id);
        return user == null ? null : new UserDto(user);
    }

    @Override
    public UserDto saveOrUdpate(UserDto dto) {
        User user = dtoUtils.toEntity(dto);
        String password = RandomStringUtils.random(15, true, true);
        user.setPassword(passwordEncoder.encode(password));
        user = userRepository.save(user);
        if (user.getId() != null) {
            mailService.sendConfirmationMail(user, password);
            return new UserDto(user);
        }
        return null;
    }


    @Override
    public List<UserDto> delete(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(String.format("Username '%s' not found", email));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }


    @Override
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUserEmail();
        if (username == null) return null;
        return userRepository.findByEmail(username);
    }

    @Override
    public Boolean ping(Token token) {
        return tokenRepository.findByEmailAndTokenAndEndDateIsNull(
                token.getEmail(), token.getToken()) != null;
    }

    @Override
    public UserFormDto authenticate(UserFormDto userFormDto) {
        if (userFormDto.getId() == null)
            return null;
        tokenService.addAuthentication(userFormDto);
        Token token = new TokenBuilder()
                .setEmail(userFormDto.getEmail())
                .setStartDate(new Date())
                .setToken(userFormDto.getToken())
                .createToken();
        tokenRepository.save(token);
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(userFormDto.getPassword(),
                        userFormDto.getPassword(), Collections.emptyList());
        return userFormDto;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
