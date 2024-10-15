package edu.ic6821.blog.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ic6821.blog.posts.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    void testCreate_PostCreatedSuccessfully() throws Exception {
        // given
        String userExtId = UUID.randomUUID().toString();
        String postExtId = UUID.randomUUID().toString();
        Long userId = 1L;

        PostContentDTO postContentDTO = new PostContentDTO("Test Title", "Test Body");
        Post post = new Post("Test Title", "Test Body", userId);
        post.setExtId(postExtId);

        when(postService.create(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(post));

        // when & then
        mockMvc.perform(post("/api/user/{userExtId}/post", userExtId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postContentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.extId").value(postExtId));
    }

    @Test
    void testCreate_UserNotFound() throws Exception {
        // given
        String userExtId = "nonExistentUser";
        PostContentDTO postContentDTO = new PostContentDTO("Test Title", "Test Body");

        when(postService.create(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        // when & then
        mockMvc.perform(post("/api/user/{userExtId}/post", userExtId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postContentDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGet_PostFound() throws Exception {
        // given
        Long userId = 1L;
        String userExtId = UUID.randomUUID().toString();
        String postExtId = UUID.randomUUID().toString();
        Post post = new Post("Test Title", "Test Body", userId);
        post.setExtId(postExtId);

        when(postService.get(postExtId)).thenReturn(Optional.of(post));

        // when & then
        mockMvc.perform(get("/api/user/{userExtId}/post/{postExtId}", userExtId, postExtId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.extId").value(postExtId))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.body").value("Test Body"));
    }

    @Test
    void testGet_PostNotFound() throws Exception {
        // given
        String userExtId = UUID.randomUUID().toString();
        String postExtId = "nonExistentPost";

        when(postService.get(postExtId)).thenReturn(Optional.empty());

        // when & then
        mockMvc.perform(get("/api/user/{userExtId}/post/{postExtId}", userExtId, postExtId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testList_PostsFoundForUser() throws Exception {
        // given
        String userExtId = UUID.randomUUID().toString();
        String post1ExtId = UUID.randomUUID().toString();
        String post2ExtId = UUID.randomUUID().toString();
        Post post1 = new Post("Title 1", "Body 1", 1L);
        post1.setExtId(post1ExtId);
        Post post2 = new Post("Title 2", "Body 2", 1L);
        post2.setExtId(post2ExtId);
        List<Post> postList = List.of(post1, post2);

        when(postService.list(userExtId)).thenReturn(Optional.of(postList));

        // when & then
        mockMvc.perform(get("/api/user/{userExtId}/post", userExtId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].extId").value(post1ExtId))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].extId").value(post2ExtId))
                .andExpect(jsonPath("$[1].title").value("Title 2"));
    }

    @Test
    void testList_UserNotFound() throws Exception {
        // given
        String userExtId = "nonExistentUser";

        when(postService.list(userExtId)).thenReturn(Optional.empty());

        // when & then
        mockMvc.perform(get("/api/user/{userExtId}/post", userExtId))
                .andExpect(status().isNotFound());
    }
}
