package edu.tec.ic6821.jdbc.model;

import edu.tec.ic6821.jdbc.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PGUserDao implements UserDao {

    private final ApplicationProperties properties;

    public PGUserDao(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    public Optional<User> create(User user) {
        try (Connection connection = openConnection()) {

            final String sql = "INSERT INTO users(username, name, created_on, last_updated_on) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                final LocalDateTime createdOn = LocalDateTime.now();
                final LocalDateTime lastUpdatedOn = LocalDateTime.now();

                statement.setString(1, user.getUsername());
                statement.setString(2, user.getName());
                statement.setTimestamp(3, Timestamp.valueOf(createdOn));
                statement.setTimestamp(4, Timestamp.valueOf(lastUpdatedOn));

                int count = statement.executeUpdate();
                if (count == 0) {
                    return Optional.empty();
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long id = generatedKeys.getLong(1);
                        User createdUser = new User(id, user.getUsername(), user.getName(), createdOn, lastUpdatedOn);
                        return Optional.of(createdUser);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = openConnection()) {

            final String sql = "SELECT * FROM users";

            try (Statement statement = connection.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    final User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created_on").toLocalDateTime(),
                            resultSet.getTimestamp("last_updated_on").toLocalDateTime()
                    );

                    users.add(user);
                }

                return users;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getDbUrl(),
                properties.getDbUsr(),
                properties.getDbPwd()
        );
    }
}
