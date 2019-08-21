package com.lee.vocabulary.service.user.impl;

import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.dao.UserDao;
import com.lee.vocabulary.service.exceptions.DuplicateUidException;
import com.lee.vocabulary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service(value = "userService")
public class DefaultUserService implements UserService {


    @Resource(name = "userDao")
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void register(UserModel userModel, String password) throws DuplicateUidException {
        try {
            userModel.setPassword(bCryptPasswordEncoder.encode(password));
            userDao.save(userModel);
        } catch (DataAccessException ex) {
            throw new DuplicateUidException(userModel.getUid(), ex);
        }
    }


    @Override
    public UserModel getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth == null) {
            return null;
        }
        String username = auth.getName();
        return userDao.findByUid(username);
    }

    @Override
    public UserModel getUserForUid(String uid) {
        return userDao.findByUid(uid);
    }
}
