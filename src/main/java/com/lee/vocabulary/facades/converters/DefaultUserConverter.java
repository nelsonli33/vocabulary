package com.lee.vocabulary.facades.converters;

import com.lee.vocabulary.core.data.UserData;
import com.lee.vocabulary.core.model.UserModel;
import org.springframework.stereotype.Component;

@Component(value = "userConverter")
public class DefaultUserConverter implements GenericConverter<UserModel, UserData> {
    @Override
    public UserData convert(UserModel source) {
        UserData target = new UserData();
        if (source != null) {
            target.setUsername(source.getUsername());
        }
        return target;
    }
}
