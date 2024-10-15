package edu.ic6821.blog.posts.model;

import edu.ic6821.blog.framework.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    private String title;
    private String body;
    private Long userId;

    public Post() {
    }

    public Post(String title, String body, Long userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getUserId(), post.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getBody(), getUserId());
    }

    @Override
    public String toString() {
        return "Post{" +
                super.toString() +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                '}';
    }
}
