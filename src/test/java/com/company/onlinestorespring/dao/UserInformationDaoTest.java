package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.Application;
import com.company.onlinestorespring.dao.impl.UserInformationDaoImpl;
import com.company.onlinestorespring.entity.UserInformation;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {UserInformationDaoImpl.class, Application.class})
public class UserInformationDaoTest {

    @Autowired
    private UserInformationDaoImpl userInformationDao;

    @Test
    public void UserInformationDao_SaveUserInformation_ReturnRowUpdated() throws EntityNotFoundException {
        UserInformation userInformation = new UserInformation();
        userInformation.setName("Alex");
        userInformation.setSurname("Rak");
        userInformation.setPatronymicName("Vladimirovich");
        userInformation.setPhoneNumber("375000000000");

        UserInformation result  = userInformationDao.saveUserInformation(userInformation);
        assertThat(result).isNotNull();

    }
}