package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.UserDaoImpl;
import com.company.onlinestorespring.entity.Role;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {UserDaoImpl.class, Application.class})
public class UserDaoTest {
    @Autowired
    private UserDaoImpl userDao;

    @Test
    public void UserDao_SaveUser_SuccessfullySaved() {
        UserInformation userInformation = new UserInformation();
        userInformation.setName("John");
        userInformation.setSurname("John");
        userInformation.setPatronymicName("John");
        userInformation.setPhoneNumber("375000000000");

        User user = new User();
        user.setUsername("johnny");
        user.setPassword("john");
        user.setEmail("johnny");
        user.setEnabled(true);
        user.setUserInformation(userInformation);
        user.setRole(new Role(1L, "ROLE_USER"));
        userDao.saveUser(user);

        User result = userDao.findUserByUsername(user.getUsername());
        System.out.println(result.getUserInformation());
        assertThat(result).isNotNull();
    }
}