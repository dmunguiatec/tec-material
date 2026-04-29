package edu.ic6821.blog.users;

import edu.ic6821.blog.users.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "User sign up")
    public SignUpDTO signup(@RequestBody UserDTO userDTO) {
        logger.info(String.format("[signup] Signing up %s", userDTO));
        try {
            Optional<User> optUser = userService.signup(
                    userDTO.username(),
                    userDTO.password(),
                    userDTO.name(),
                    userDTO.email()
            );

            return optUser
                    .map(user -> new SignUpDTO(SignUpStatus.SIGN_UP_SUCCEEDED, user.getUsername(), user.getExtId()))
                    .orElseGet(() -> new SignUpDTO(SignUpStatus.SIGN_UP_FAILED_USERNAME_EXISTS, userDTO.username(), null));
        } catch (Exception e) {
            logger.error(String.format("[signup] Unexpected error %s: %s", e.getClass(), e.getMessage()), e);
            throw e;
        }
    }
}