package com.ic6821.blog.users;

import com.ic6821.blog.users.model.User;
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
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpDTO signup(@RequestBody UserDTO userDTO) {
        logger.info(String.format("[signup] Signing up %s", userDTO));
        try {
            Optional<User> optUser = userService.signup(
                    userDTO.username(),
                    userDTO.password(),
                    userDTO.name(),
                    userDTO.email()
            );

            return optUser.map(user -> new SignUpDTO(SignUpStatus.SIGN_UP_SUCCEEDED, user.getUsername(), user.getExtId()))
                    .orElseGet(() -> new SignUpDTO(SignUpStatus.SIGN_UP_FAILED_USERNAME_EXISTS, userDTO.username(), null));
        } catch (Exception e) {
            logger.error(String.format("[signup] Unexpected error %s: %s", e.getClass(), e.getMessage()), e);
            throw e;
        }
    }
}
