package edu.ic6821.blog.posts;

import edu.ic6821.blog.posts.model.Post;
import edu.ic6821.blog.users.UserRepository;
import edu.ic6821.blog.users.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryIntegrationTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByExtId() {
        // given
        final User newUser = new User("username", "password", "name", "email");
        final User savedUser = userRepository.save(newUser);
        final Post newPost = new Post("title", "body", savedUser.getId());
        final Post savedPost = postRepository.save(newPost);

        final Optional<Post> optPost = postRepository.findById(savedPost.getId());
        assertThat(optPost).isPresent();
        final Post retrievedPost = optPost.get();

        // when
        final Optional<Post> actual = postRepository.findByExtId(retrievedPost.getExtId());

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(retrievedPost);
    }

    @Test
    public void findByExtIdNoResult() {
        // given
        final String nonExistingId = UUID.randomUUID().toString();

        // when
        final Optional<Post> actual = postRepository.findByExtId(nonExistingId);

        // then
        assertThat(actual).isEmpty();
    }
}
