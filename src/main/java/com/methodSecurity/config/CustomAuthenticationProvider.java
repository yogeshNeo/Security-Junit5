package com.methodSecurity.config;

import com.methodSecurity.entity.UserInfo;
import com.methodSecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserInfoRepository repository;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Optional<UserInfo> byUsername = repository.findByUsername(username);

        if (byUsername.isEmpty() || !byUsername.get().getUsername().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        boolean isPasswordMatches = new BCryptPasswordEncoder().matches(password, byUsername.get().getPassword());

        if (!isPasswordMatches) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = byUsername.get().getAuthorities();

        return new UsernamePasswordAuthenticationToken(byUsername.get(), password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}