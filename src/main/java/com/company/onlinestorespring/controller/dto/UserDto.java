package com.company.onlinestorespring.controller.dto;

import com.company.onlinestorespring.service.validation.FieldMatch;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "{validation.passwordMatch}")})
public class UserDto {

    @NotNull(message = "{validation.usernameIsRequired}")
    @Size(min = 4, max = 10, message = "{validation.usernameSize}")
    private String username;

    @NotNull(message = "{validation.emailIsRequired}")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "{validation.email}")
    private String email;

    @NotNull(message = "{validation.passwordIsRequired}")
    @Size(min = 8, max = 20, message = "{validation.password}")
    private String password;
    private String matchingPassword;

    @NotNull(message = "{validation.nameIsRequired}")
    @Size(min = 2, max = 20, message = "{validation.nameSize}")
    private String name;

    @NotNull(message = "{validation.surnameIsRequired}")
    @Size(min = 2, max = 20, message = "{validation.surnameSize}")
    private String surname;

    @NotNull(message = "{validation.patronymicIsRequired}")
    @Size(min = 2, max = 20, message = "{validation.patronymicNameSize}")
    private String patronymic;

    @NotNull(message = "{validation.phoneIsRequired}")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "{validation.phoneNumber}")
    private String phone;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
