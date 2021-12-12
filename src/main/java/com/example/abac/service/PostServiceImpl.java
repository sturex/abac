package com.example.abac.service;

import com.example.abac.entity.Post;
import com.example.abac.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Post findPostById(int postId) {
        return postRepository.findPostById(postId)
                .orElseThrow(() -> new RuntimeException("Not found Post with id: " + postId));
    }

    @Override
    @PreAuthorize("hasPermission(#post, 'delete')")
    public Post deletePost(Post post) {
        return postRepository.deletePost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPostsOrderByPostId();
    }

    @Override
    public Post createPost(String content) {
        User user = userService.fromContext();
        return postRepository.createPost(user, content);
    }
}
