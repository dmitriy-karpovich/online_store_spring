package com.company.onlinestorespring.config;

import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession session = request.getSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();

        Optional<User> user;
        user = userService.retrieveUserByUsername(username);
        session.setAttribute("user", user.get());
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(request.getContextPath() + "/main");
        LOGGER.info("User with the username {} has successfully logged in.", username);
    }
}