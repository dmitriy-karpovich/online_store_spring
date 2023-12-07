package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.User;
import java.util.List;

public interface UserDao {

    /**
     * Find a user in the database by username
     *
     * @param username - the username
     * @return the user
     */
    User findUserByUsername(String username);

    /**
     * Find a user in the database by id
     *
     * @param id - the id
     * @return the user
     */
    User findUserById(Long id);

    /**
     * Save a new user in the database
     *
     * @param user - the user
     * @return
     */
    User saveUser(User user);

    /**
     * Find all users in the database
     *
     * @return the list of users
     */
    List<User> findAllUsers();
}