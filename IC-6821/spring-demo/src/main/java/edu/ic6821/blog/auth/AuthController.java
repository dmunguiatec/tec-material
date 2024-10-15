package edu.ic6821.blog.auth;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public static final String EMPTY_TOKEN = "";

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login")
    public AuthDTO login(@RequestBody CredentialsDTO credentials) {
        logger.info(String.format("[login] Authenticating %s", credentials.username()));
        try {
            Optional<String> optToken = authService.authenticate(credentials.username(), credentials.password());
            return optToken.map(token -> new AuthDTO(AuthStatus.AUTH_SUCCEEDED, token))
                    .orElseGet(() -> new AuthDTO(AuthStatus.AUTH_FAILED, EMPTY_TOKEN));
        } catch (Exception e) {
            logger.error(String.format("[login] Unexpected error %s: %s", e.getClass(), e.getMessage()), e);
            throw e;
        }
    }
}
