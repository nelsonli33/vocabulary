package com.lee.vocabulary.core.security.impl;

import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CoreUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userDao.findByUid(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        return new User(user.getUid(), user.getPassword(), Collections.emptyList());
    }
}
