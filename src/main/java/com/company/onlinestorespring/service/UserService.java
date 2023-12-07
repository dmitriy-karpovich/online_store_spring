package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.User;
import java.util.Optional;

public interface UserService {

    /**
     * Retrieve a user by id
     *
     * @param userId - the id
     * @return the user
     */
    User retrieveUserById(Long userId);

    /**
     * Retrieve a user by username
     *
     * @param username - the username
     * @return the user
     */
    Optional<User> retrieveUserByUsername(String username);

    /**
     * Register a new user
     *
     * @param user - the user
     */
    void saveUser(User user);
}