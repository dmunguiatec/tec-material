package edu.ic6821.blog.users;

import edu.ic6821.blog.users.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testSignup_Success() throws Exception {
        // Given
        UserDTO userDTO = new UserDTO("username", "password", "name", "email");
        User user = new User("username", "password", "name", "email");
        user.setExtId("user123");

        when(userService.signup(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(user));

        // When & Then
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "username": "username",
                                        "password": "password",
                                        "name": "name",
                                        "email": "email"
                                    }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(SignUpStatus.SIGN_UP_SUCCEEDED.name()))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.extId").value("user123"));
    }

    @Test
    void testSignup_UsernameExists() throws Exception {
        // Given
        UserDTO userDTO = new UserDTO("existingUser", "password", "name", "email");

        when(userService.signup(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "username": "existingUser",
                                        "password": "password",
                                        "name": "name",
                                        "email": "email"
                                    }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(SignUpStatus.SIGN_UP_FAILED_USERNAME_EXISTS.name()))
                .andExpect(jsonPath("$.username").value("existingUser"))
                .andExpect(jsonPath("$.extId").doesNotExist());
    }
}
