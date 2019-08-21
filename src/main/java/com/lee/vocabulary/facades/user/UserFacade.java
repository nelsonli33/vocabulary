package com.lee.vocabulary.facades.user;

import com.lee.vocabulary.core.data.RegisterData;
import com.lee.vocabulary.core.data.UserData;
import com.lee.vocabulary.service.exceptions.DuplicateUidException;

public interface UserFacade {

    String getCurrentUserUid();

    UserData getCurrentUser();

    void register(RegisterData registerData) throws DuplicateUidException;

    boolean isAccountExists(String uid);
}
