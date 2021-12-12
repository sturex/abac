package com.example.abac.service;

import com.example.abac.entity.Post;

import java.util.List;

public interface PostService {
    Post findPostById(int postId);

    Post deletePost(Post post);

    List<Post> getAllPosts();

    Post createPost(String content);
}
