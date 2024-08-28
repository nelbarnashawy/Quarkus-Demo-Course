package com.sumerge.security;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtService {

    public String generateJwt() {
        Set<String> roles = new HashSet<>(
                Arrays.asList("user", "admin")
                );
        return Jwt.issuer("jwt-issuer")
                .subject("jwt-subject")
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 6000)
                .sign();
    }
}
