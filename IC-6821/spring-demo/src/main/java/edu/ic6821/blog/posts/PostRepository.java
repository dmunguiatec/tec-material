package edu.ic6821.blog.posts;

import edu.ic6821.blog.posts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByExtId(String extId);
    List<Post> findByUserId(Long userId);
}
