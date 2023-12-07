package com.company.onlinestorespring.dao;

import com.company.onlinestorespring.entity.UserInformation;

public interface UserInformationDao {

    /**
     * Find user information in the database by id
     *
     * @param id - the id
     * @return  the user information
     */
    UserInformation findUserInformationById(Long id);

    /**
     * Save information about a new user in the database
     *
     * @param userInformation - the user information
     */
    UserInformation saveUserInformation(UserInformation userInformation);
}