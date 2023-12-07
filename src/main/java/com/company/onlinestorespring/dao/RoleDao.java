package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.Role;

public interface RoleDao {

    /**
     * Find a role in the database by name
     *
     * @param name - the name
     * @return the role
     */
    Role findRoleByName(String name);
}