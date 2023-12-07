package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.controller.UserController;
import com.company.onlinestorespring.dao.RoleDao;
import com.company.onlinestorespring.dao.UserDao;
import com.company.onlinestorespring.entity.Role;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import com.company.onlinestorespring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User retrieveUserById(Long userId) {
        User user = userDao.findUserById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User was not found by id" + userId);
        }
        return user;
    }

    @Override
    public Optional<User> retrieveUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        Role role = roleDao.findRoleByName(USER_ROLE);
        if (role == null) {
            throw new EntityNotFoundException("Role was not found with the name " + USER_ROLE);
        }
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userDao.saveUser(user);
        LOGGER.info("The user with username {} has been successfully registered.", result.getUsername());
    }
}