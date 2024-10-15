package edu.ic6821.blog.posts;

import edu.ic6821.blog.posts.model.Post;
import edu.ic6821.blog.users.UserRepository;
import edu.ic6821.blog.users.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_PostCreationSuccess() {
        // given
        String title = "Test Title";
        String body = "Test Body";
        String userExtId = UUID.randomUUID().toString();
        Long postId = 1L;

        User user = new User();
        Post post = new Post(title, body, user.getId());
        post.setId(postId);

        when(userRepository.findByExtId(userExtId)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(post);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // when
        Optional<Post> actual = postService.create(title, body, userExtId);

        // then
        assertTrue(actual.isPresent());
        assertEquals(postId, actual.get().getId());
        assertEquals(title, actual.get().getTitle());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void testCreate_UserNotFound_ShouldReturnEmpty() {
        // given
        String title = "Test Title";
        String body = "Test Body";
        String userExtId = "nonExistentUser";

        when(userRepository.findByExtId(userExtId)).thenReturn(Optional.empty());

        // when
        Optional<Post> actual = postService.create(title, body, userExtId);

        // then
        assertTrue(actual.isEmpty());
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void testGet_PostFound() {
        // given
        String postExtId = UUID.randomUUID().toString();
        Post post = new Post("Test Title", "Test Body", 1L);
        post.setExtId(postExtId);

        when(postRepository.findByExtId(postExtId)).thenReturn(Optional.of(post));

        // when
        Optional<Post> foundPost = postService.get(postExtId);

        // then
        assertTrue(foundPost.isPresent());
        assertEquals(postExtId, foundPost.get().getExtId());
        verify(postRepository).findByExtId(postExtId);
    }

    @Test
    void testGet_PostNotFound() {
        // given
        String postExtId = "nonExistentPost";

        when(postRepository.findByExtId(postExtId)).thenReturn(Optional.empty());

        // when
        Optional<Post> foundPost = postService.get(postExtId);

        // then
        assertTrue(foundPost.isEmpty());
    }

    @Test
    void testList_PostsFoundForUser() {
        // given
        String userExtId = UUID.randomUUID().toString();
        User user = new User();
        user.setId(1L);
        Post post1 = new Post("Title 1", "Body 1", user.getId());
        Post post2 = new Post("Title 2", "Body 2", user.getId());
        List<Post> postList = List.of(post1, post2);

        when(userRepository.findByExtId(userExtId)).thenReturn(Optional.of(user));
        when(postRepository.findByUserId(user.getId())).thenReturn(postList);

        // when
        Optional<List<Post>> posts = postService.list(userExtId);

        // then
        assertTrue(posts.isPresent());
        assertEquals(2, posts.get().size());
        verify(postRepository).findByUserId(user.getId());
    }

    @Test
    void testList_UserNotFound_ShouldReturnEmpty() {
        // given
        String userExtId = "nonExistentUser";

        when(userRepository.findByExtId(userExtId)).thenReturn(Optional.empty());

        // when
        Optional<List<Post>> posts = postService.list(userExtId);

        // then
        assertTrue(posts.isEmpty());
        verify(postRepository, never()).findByUserId(any(Long.class));
    }
}
