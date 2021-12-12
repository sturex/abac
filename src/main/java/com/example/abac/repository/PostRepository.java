package com.example.abac.repository;

import com.example.abac.entity.Post;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private final Map<Integer, Post> posts = new HashMap<>();

    public Optional<Post> findPostById(int postId) {
        return Optional.ofNullable(posts.get(postId));
    }

    public Post deletePost(Post post) {
        return posts.remove(post.getId());
    }

    public List<Post> getAllPostsOrderByPostId() {
        return posts.values().stream()
                .sorted(Comparator.comparingInt(Post::getId))
                .collect(Collectors.toList());
    }

    public Post createPost(User user, String content) {
        int postId = posts.size() + 1;
        return posts.computeIfAbsent(postId, id ->  Post.builder()
                .id(postId)
                .author(user)
                .content(content)
                .build());
    }
}
