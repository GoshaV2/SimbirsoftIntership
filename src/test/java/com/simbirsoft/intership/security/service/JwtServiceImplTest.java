package com.simbirsoft.intership.security.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;

class JwtServiceImplTest {
    private JwtServiceImpl jwtService;

    @BeforeEach
    public void setFields() {
        jwtService = new JwtServiceImpl();
        ReflectionTestUtils.setField(jwtService, "SECRET_KEY", "35753878214125442A472D4B6150645367566B597033733676397924423F4528");
        ReflectionTestUtils.setField(jwtService, "expiration", 1440);
    }

    @Test
    void generateToken_WhenIsValid() {
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "1234567890";
            }

            @Override
            public String getUsername() {
                return "ivanov@mail.ru";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        String newToken = jwtService.generateToken(userDetails);
        Assertions.assertTrue(jwtService.isTokenValid(newToken, userDetails));
    }
}