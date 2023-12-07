package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.RoleDao;
import com.company.onlinestorespring.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final String FIND_ROLE_BY_NAME = "select e from Role e where e.roleName=:roleName";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(FIND_ROLE_BY_NAME, Role.class);
        query.setParameter("roleName", name);
        return query.getSingleResult();
    }
}