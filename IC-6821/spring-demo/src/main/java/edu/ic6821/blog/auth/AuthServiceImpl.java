package edu.ic6821.blog.auth;

import edu.ic6821.blog.users.UserRepository;
import edu.ic6821.blog.users.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    public static final int EXPIRATION_TIME_MILLIS = 86400000;
    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<String> authenticate(String username, String password) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (encoder.check(password, user.getPassword())) {
                return Optional.of(generateToken(username));
            }
        }

        return Optional.empty();
    }

    @Override
    public IdentityDTO validateToken(String token) {
        final String username = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return new IdentityDTO(username);
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS))
                .signWith(SECRET_KEY)
                .compact();
    }
}
