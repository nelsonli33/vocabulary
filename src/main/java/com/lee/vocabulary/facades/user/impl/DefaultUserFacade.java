package com.lee.vocabulary.facades.user.impl;

import com.google.common.base.Preconditions;
import com.lee.vocabulary.core.data.RegisterData;
import com.lee.vocabulary.core.data.UserData;
import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.facades.converters.GenericConverter;
import com.lee.vocabulary.facades.user.UserFacade;
import com.lee.vocabulary.service.exceptions.DuplicateUidException;
import com.lee.vocabulary.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component(value = "userFacade")
public class DefaultUserFacade implements UserFacade {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "userConverter")
    private GenericConverter<UserModel, UserData> userConverter;


    @Override
    public UserData getCurrentUser() {
        return userConverter.convert(internalGetCurrentUser());
    }

    @Override
    public String getCurrentUserUid() {
        UserModel userModel = internalGetCurrentUser();
        if (userModel != null) {
            return userModel.getUid();
        }
        return "";
    }

    private UserModel internalGetCurrentUser() {
        return userService.getCurrentUser();
    }

    @Override
    public void register(RegisterData registerData) throws DuplicateUidException {
        Preconditions.checkArgument(registerData != null, "registerData cannot be null");
        Assert.hasText(registerData.getUid(), "The field [uid] cannot be empty");
        Assert.hasText(registerData.getUsername(), "The field [UserName] cannot be empty");
        Assert.hasText(registerData.getPassword(), "The field [Password] cannot be empty");

        UserModel userModel = new UserModel();
        userModel.setUid(registerData.getUid().toLowerCase());
        userModel.setUsername(registerData.getUsername());
        userModel.setGrade(registerData.getGrade());
        userModel.setGender(registerData.getGender());
        userService.register(userModel, registerData.getPassword());
    }

    @Override
    public boolean isAccountExists(String uid) {
        if (userService.getUserForUid(uid) == null) {
            return false;
        }
        return true;
    }
}
