package com.example.abac.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Builder
@Getter
@Setter
public class Post {
    private int id;
    private User author;
    private String content;
}
