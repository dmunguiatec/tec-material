package com.ic6821.blog.auth;

import com.ic6821.blog.users.model.User;

import java.util.Optional;

public interface AuthService {
    Optional<String> authenticate(String username, String password);
    IdentityDTO validateToken(String token);
}
