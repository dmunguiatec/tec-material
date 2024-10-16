package edu.ic6821.blog.posts;

import edu.ic6821.blog.posts.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/{userExtId}/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @PostMapping
    @Operation(summary = "Create new post")
    @SecurityRequirement(name = "Bearer Authentication")
    public PostIdDTO create(@RequestBody PostContentDTO postDTO, @PathVariable String userExtId) {
        logger.info(String.format("[%s.create] Creating new post %s for user %s",
                this.getClass(), postDTO.title(), userExtId));

        try {
            Optional<Post> optPost = postService.create(postDTO.title(), postDTO.body(), userExtId);
            if (optPost.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return new PostIdDTO(optPost.get().getExtId());
        } catch (Exception e) {
            logger.error(String.format("[%s.create] Unexpected error %s: %s", this.getClass(), e.getClass(), e.getMessage()), e);
            throw e;
        }
    }

    @GetMapping("/{postExtId}")
    @Operation(summary = "Get post content")
    @SecurityRequirement(name = "Bearer Authentication")
    public PostDetailDTO get(@PathVariable String userExtId, @PathVariable String postExtId) {
        logger.info(String.format("[%s.get] Retrieving post %s",
                this.getClass(), postExtId));

        try {
            Optional<Post> optPost = postService.get(postExtId);
            if (optPost.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Post post = optPost.get();
            return new PostDetailDTO(post.getExtId(), post.getTitle(), post.getBody());
        } catch (Exception e) {
            logger.error(String.format("[%s.get] Unexpected error %s: %s", this.getClass(), e.getClass(), e.getMessage()), e);
            throw e;
        }
    }


    @GetMapping
    @Operation(summary = "List all posts by user")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<PostHeaderDTO> list(@PathVariable String userExtId) {
        logger.info(String.format("[%s.get] Retrieving posts for user %s",
                this.getClass(), userExtId));

        try {
            Optional<List<Post>> optPosts = postService.list(userExtId);
            if (optPosts.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            List<Post> posts = optPosts.get();
            return posts.stream()
                    .map(post -> new PostHeaderDTO(post.getExtId(), post.getTitle()))
                    .toList();
        } catch (Exception e) {
            logger.error(String.format("[%s.get] Unexpected error %s: %s", this.getClass(), e.getClass(), e.getMessage()), e);
            throw e;
        }
    }

}
