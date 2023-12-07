package com.company.onlinestorespring.service;

import com.company.onlinestorespring.dao.impl.RoleDaoImpl;
import com.company.onlinestorespring.dao.impl.UserDaoImpl;
import com.company.onlinestorespring.entity.Role;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserInformation;
import com.company.onlinestorespring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDaoImpl userDao;
    @Mock
    private RoleDaoImpl roleDao;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    private static final String USER_ROLE = "ROLE_USER";

    @Test
    public void UserService_SaveUser_SuccessfullySaved() {
        Role role = new Role(1L, USER_ROLE);
        UserInformation userInformation = new UserInformation();
        userInformation.setName("Alex");
        userInformation.setSurname("Alex");
        userInformation.setPatronymicName("Alex");
        userInformation.setPhoneNumber("375000000000");

        User user = new User();
        user.setUsername("john");
        user.setEmail("john@mail.com");
        user.setPassword(bCryptPasswordEncoder.encode("john"));
        user.setEnabled(true);
        user.setUserInformation(userInformation);
        user.setRole(role);

        when(roleDao.findRoleByName(Mockito.any(String.class))).thenReturn(role);
        when(userDao.findUserByUsername(Mockito.any(String.class))).thenReturn(user);
        userService.saveUser(user);

        Optional<User> result = userService.retrieveUserByUsername("john");
        assertThat(result).isNotEmpty();
    }

    @Test
    public void UserService_FindUserByUsername_ReturnUser() {
        User user = new User();
        user.setUsername("john");
        user.setId(6L);
        user.setEmail("alex1234@gmail.com");
        user.setRole(new Role(1L, "ROLE_USER"));
        user.setUserInformation(new UserInformation(6L, "Александр",
                        "Александров", "Александрович","375291234567"));

        when(userDao.findUserByUsername(Mockito.any(String.class))).thenReturn(user);
        Optional<User> result = userService.retrieveUserByUsername(user.getUsername());
        assertThat(result).isNotEmpty();
    }
}