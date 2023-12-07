package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.UserDao;
import com.company.onlinestorespring.entity.Role;
import com.company.onlinestorespring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User was not found by username");
            }
            Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRole());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                                                          authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Role role) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(authority);
        return authorities;
    }
}