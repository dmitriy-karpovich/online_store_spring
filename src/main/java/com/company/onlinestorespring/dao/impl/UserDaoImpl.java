package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.UserDao;
import com.company.onlinestorespring.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String FIND_USER_BY_ID = "select u from User u where u.id=:id";
    private static final String FIND_USER_BY_USERNAME = "select u from User u where u.username=:username";
    private static final String FIND_ALL_USERS = "select u from User u";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserByUsername(String username) {
         TypedQuery<User> query = entityManager.createQuery(FIND_USER_BY_USERNAME, User.class);
         query.setParameter("username", username);
         return query.getSingleResult();
    }

    @Override
    public User findUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(FIND_USER_BY_ID, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery(FIND_ALL_USERS, User.class)
                .getResultList();
    }
}