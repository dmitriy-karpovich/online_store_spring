package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.UserInformationDao;
import com.company.onlinestorespring.entity.UserInformation;
import com.company.onlinestorespring.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    private final UserInformationDao userInformationDao;

    @Autowired
    public UserInformationServiceImpl(UserInformationDao userInformationDao) {
        this.userInformationDao = userInformationDao;
    }

    @Override
    public Optional<UserInformation> retrieveUserInformationById(Long id) {
        UserInformation userInformation = userInformationDao.findUserInformationById(id);
        return Optional.ofNullable(userInformation);
    }
}