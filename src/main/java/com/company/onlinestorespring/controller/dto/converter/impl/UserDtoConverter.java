package com.company.onlinestorespring.controller.dto.converter.impl;

import com.company.onlinestorespring.controller.dto.UserDto;
import com.company.onlinestorespring.controller.dto.converter.DtoConverter;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserInformation;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements DtoConverter<User, UserDto> {

    @Override
    public User convertToEntity(UserDto dto) {
        UserInformation userInformation = new UserInformation();
        userInformation.setName(dto.getName());
        userInformation.setSurname(dto.getSurname());
        userInformation.setPatronymicName(dto.getPatronymic());
        userInformation.setPhoneNumber(dto.getPhone());

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setEnabled(true);
        user.setUserInformation(userInformation);
        return user;
    }

    @Override
    public UserDto convertToDto(User entity) {
        return null;
    }
}
