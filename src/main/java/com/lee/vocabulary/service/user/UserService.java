package com.lee.vocabulary.service.user;


import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.service.exceptions.DuplicateUidException;

public interface UserService {

    void register(UserModel userModel, String password) throws DuplicateUidException;

    UserModel getCurrentUser();

    UserModel getUserForUid(String uid);
}
