package com.example.abac.config;

import com.example.abac.entity.Post;
import com.example.abac.permission.AbacPermissionContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;

@Configuration
public class AbacPermissionConfig {

    @Bean
    public AbacPermissionContainer permissionContainer() {
        AbacPermissionContainer abacPermissionContainer = new AbacPermissionContainer();

        abacPermissionContainer.put("delete", Post.class, environment -> {
            Post post = environment.getTargetObject();
            User user = environment.getUser();
            return post.getAuthor().equals(user);
        });

        return abacPermissionContainer;
    }

}
