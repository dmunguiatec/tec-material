package edu.ic6821.blog.auth;

import java.util.Optional;

public interface AuthService {
    Optional<String> authenticate(String username, String password);
    IdentityDTO validateToken(String token);
}
