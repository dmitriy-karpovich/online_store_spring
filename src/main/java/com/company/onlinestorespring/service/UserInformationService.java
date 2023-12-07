package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.UserInformation;
import java.util.Optional;

public interface UserInformationService {

    /**
     * Retrieve user information by id
     *
     * @param userInformationId - the user information id
     * @return the user information
     */
    Optional<UserInformation> retrieveUserInformationById(Long userInformationId);
}