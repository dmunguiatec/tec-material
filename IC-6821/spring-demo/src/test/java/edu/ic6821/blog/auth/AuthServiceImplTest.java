package edu.ic6821.blog.auth;

import edu.ic6821.blog.users.UserRepository;
import edu.ic6821.blog.users.model.User;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthServiceImplTest {

    @InjectMocks
    private AuthService authService = new AuthServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    @Test
    public void testAuthenticate_Success() {
        // Given
        String username = "testuser";
        String password = "password";
        String encodedPassword = "encodedPassword";

        User user = new User(username, encodedPassword, "name", "email");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.check(password, encodedPassword)).thenReturn(true);

        // When
        Optional<String> token = authService.authenticate(username, password);

        // Then
        assertTrue(token.isPresent());
        assertNotNull(token.get());
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).check(password, encodedPassword);
    }

    @Test
    public void testAuthenticate_Failure_UserNotFound() {
        // Given
        String username = "nonexistentuser";
        String password = "password";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When
        Optional<String> token = authService.authenticate(username, password);

        // Then
        assertFalse(token.isPresent());
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, never()).check(anyString(), anyString());
    }

    @Test
    public void testAuthenticate_Failure_InvalidPassword() {
        // Given
        String username = "testuser";
        String password = "wrongpassword";
        String encodedPassword = "encodedPassword";
        User user = new User(username, encodedPassword, "name", "email");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.check(password, encodedPassword)).thenReturn(false);

        // When
        Optional<String> token = authService.authenticate(username, password);

        // Then
        assertFalse(token.isPresent());
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).check(password, encodedPassword);
    }

    @Test
    public void testValidateToken_ValidToken() {
        // Given
        String username = "testuser";
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + AuthServiceImpl.EXPIRATION_TIME_MILLIS))
                .signWith(AuthServiceImpl.SECRET_KEY)
                .compact();

        // When
        IdentityDTO identityDTO = authService.validateToken(token);

        // Then
        assertNotNull(identityDTO);
        assertEquals(username, identityDTO.username());
    }

    @Test
    public void testValidateToken_InvalidToken() {
        // Given
        String invalidToken = "invalidToken";

        // When & Then
        assertThrows(io.jsonwebtoken.JwtException.class, () -> {
            authService.validateToken(invalidToken);
        });
    }
}
