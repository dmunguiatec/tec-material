package com.ic6821.blog.posts;

import com.ic6821.blog.posts.model.Post;
import com.ic6821.blog.users.UserRepository;
import com.ic6821.blog.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Post> create(String title, String body, String userExtId) {
        Optional<User> optUser = userRepository.findByExtId(userExtId);
        if (optUser.isEmpty()) {
            return Optional.empty();
        }

        Post post = new Post(title, body, optUser.get().getId());
        Post postWithId = postRepository.save(post);

        return postRepository.findById(postWithId.getId());
    }

    @Override
    public Optional<Post> get(String postExtId) {
        return postRepository.findByExtId(postExtId);
    }

    @Override
    public Optional<List<Post>> list(String userExtId) {
        Optional<User> optUser = userRepository.findByExtId(userExtId);
        return optUser.map(user -> postRepository.findByUserId(user.getId()));
    }
}
