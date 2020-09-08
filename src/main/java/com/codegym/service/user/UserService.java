package com.codegym.service.user;

import com.codegym.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
