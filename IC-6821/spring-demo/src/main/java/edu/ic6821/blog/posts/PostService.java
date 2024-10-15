package edu.ic6821.blog.posts;

import edu.ic6821.blog.posts.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> create(String title, String body, String userExtId);

    Optional<Post> get(String postExtId);

    Optional<List<Post>> list(String userExtId);
}
