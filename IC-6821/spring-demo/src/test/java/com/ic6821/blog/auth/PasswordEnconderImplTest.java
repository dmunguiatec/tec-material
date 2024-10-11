package com.ic6821.blog.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderImplTest {

    private PasswordEncoderImpl passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @Test
    void testHash_ShouldReturnHashedPassword() {
        // Given
        String rawPassword = "mySecretPassword";

        // When
        String hashedPassword = passwordEncoder.hash(rawPassword);

        // Then
        assertNotNull(hashedPassword);
        assertNotEquals(rawPassword, hashedPassword);
        assertTrue(hashedPassword.startsWith("$2a$"));
    }

    @Test
    void testCheck_ShouldReturnTrueForMatchingPasswords() {
        // Given
        String rawPassword = "mySecretPassword";
        String hashedPassword = passwordEncoder.hash(rawPassword);

        // When
        boolean matches = passwordEncoder.check(rawPassword, hashedPassword);

        // Then
        assertTrue(matches);
    }

    @Test
    void testCheck_ShouldReturnFalseForNonMatchingPasswords() {
        // Given
        String rawPassword = "mySecretPassword";
        String hashedPassword = passwordEncoder.hash(rawPassword);

        // When
        boolean matches = passwordEncoder.check("wrongPassword", hashedPassword);

        // Then
        assertFalse(matches);
    }
}
