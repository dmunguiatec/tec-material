package edu.ic6821.blog.users;

import edu.ic6821.blog.auth.PasswordEncoder;
import edu.ic6821.blog.users.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup_UserAlreadyExists_ShouldReturnEmpty() {
        // given
        String username = "existingUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        // when
        Optional<User> result = userService.signup(username, "password", "name", "email");

        // then
        assertTrue(result.isEmpty());
        verify(userRepository, never()).save(any(User.class));
        verify(encoder, never()).hash(anyString());
    }

    @Test
    void testSignup_NewUser_ShouldReturnSavedUser() {
        // given
        String username = "newUser";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        User newUser = new User(username, encodedPassword, "name", "email");
        newUser.setId(1L); // Mock user with ID after save

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(encoder.hash(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(userRepository.findById(1L)).thenReturn(Optional.of(newUser));

        // when
        Optional<User> result = userService.signup(username, rawPassword, "name", "email");

        // then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals(username, result.get().getUsername());
        verify(userRepository).save(any(User.class));
        verify(encoder).hash(rawPassword);
    }
}
