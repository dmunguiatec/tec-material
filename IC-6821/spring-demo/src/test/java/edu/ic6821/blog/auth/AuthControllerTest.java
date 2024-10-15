package edu.ic6821.blog.auth;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper(); // For converting objects to JSON
    }

    @Test
    public void testLogin_Success() throws Exception {
        // Given
        String username = "testuser";
        String password = "password";
        String token = "sampleToken";

        CredentialsDTO credentials = new CredentialsDTO(username, password);
        AuthDTO expectedAuthDTO = new AuthDTO(AuthStatus.AUTH_SUCCEEDED, token);

        // When
        when(authService.authenticate(username, password)).thenReturn(Optional.of(token));

        // Then
        mockMvc.perform(post("/api/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(AuthStatus.AUTH_SUCCEEDED.name()))
                .andExpect(jsonPath("$.token").value(token));

        // Verify
        verify(authService, times(1)).authenticate(username, password);
    }

    @Test
    public void testLogin_Failure() throws Exception {
        // Given
        String username = "testuser";
        String password = "wrongPassword";

        CredentialsDTO credentials = new CredentialsDTO(username, password);
        AuthDTO expectedAuthDTO = new AuthDTO(AuthStatus.AUTH_FAILED, AuthController.EMPTY_TOKEN);

        // When
        when(authService.authenticate(username, password)).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(post("/api/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(AuthStatus.AUTH_FAILED.name()))
                .andExpect(jsonPath("$.token").value(AuthController.EMPTY_TOKEN));

        // Verify
        verify(authService, times(1)).authenticate(username, password);
    }
}
