package com.example.abac.controller;

import com.example.abac.entity.Post;
import com.example.abac.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @MutationMapping("deletePost")
    public Post deletePost(@Argument int postId) {
        Post post = postService.findPostById(postId);
        return postService.deletePost(post);
    }

    @MutationMapping("createPost")
    public Post createPost(@Argument String content) {
        return postService.createPost(content);
    }

    @QueryMapping("getAllPosts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

}
