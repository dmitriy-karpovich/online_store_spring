package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.RoleDaoImpl;
import com.company.onlinestorespring.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {RoleDaoImpl.class, Application.class})
public class RoleDaoTest {

    @Autowired
    private RoleDaoImpl roleDao;

    @Test
    public void RoleDao_FindRoleByName_ReturnRole() {
        String roleName = "ROLE_USER";
        Role result = roleDao.findRoleByName(roleName);
        assertThat(result).isNotNull();
    }
}