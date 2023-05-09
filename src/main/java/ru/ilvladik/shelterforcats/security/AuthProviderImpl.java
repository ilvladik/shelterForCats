package ru.ilvladik.shelterforcats.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ilvladik.shelterforcats.services.ManagerDetailsService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final ManagerDetailsService managerDetailsService;

    @Autowired
    public AuthProviderImpl(ManagerDetailsService managerDetailsService) {
        this.managerDetailsService = managerDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails managerDetails = managerDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();

        if (!password.equals(managerDetails.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }

        return new UsernamePasswordAuthenticationToken(managerDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
