package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.UserInformationDao;
import com.company.onlinestorespring.entity.UserInformation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserInformationDaoImpl implements UserInformationDao {
    private static final String FIND_USER_INFORMATION_BY_ID = "select ui from UserInformation ui where ui.id=:id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInformation findUserInformationById(Long id) {
        TypedQuery<UserInformation> query = entityManager
                .createQuery(FIND_USER_INFORMATION_BY_ID, UserInformation.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public UserInformation saveUserInformation(UserInformation userInformation) {
        entityManager.persist(userInformation);
        return userInformation;
    }
}