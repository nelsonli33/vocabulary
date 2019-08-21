package com.lee.vocabulary.core.data;

import com.lee.vocabulary.core.enums.GenderType;
import lombok.Data;

@Data
public class RegisterData {
    private String uid;
    private String password;
    private String username;
    private String grade;
    private GenderType gender;
}
