package com.example.abac.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
@AllArgsConstructor
public
class AbacPermissionEnvironment<T> {
    private final User user;
    private final T targetObject;
}
