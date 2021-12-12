package com.example.abac.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    User fromContext();

    User fromAuthentication(Authentication authentication);
}
