package edu.ic6821.blog.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String password) {
        return this.encoder.encode(password);
    }

    @Override
    public boolean check(String rawPassword, String hashedPassword) {
        return this.encoder.matches(rawPassword, hashedPassword);
    }
}
