package com.example.abac.permission;

import com.example.abac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AbacPermissionEvaluator implements PermissionEvaluator {

    private final AbacPermissionContainer abacPermissionContainer;
    private final UserService userService;

    @Autowired
    public AbacPermissionEvaluator(AbacPermissionContainer abacPermissionContainer,
                                   UserService userService) {
        this.abacPermissionContainer = abacPermissionContainer;
        this.userService = userService;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {
        User user = userService.fromAuthentication(authentication);
        return abacPermissionContainer.evaluate(permission.toString(),
                new AbacPermissionEnvironment<>(user, targetDomainObject));
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
