package com.lee.vocabulary.web.form;

import com.lee.vocabulary.core.enums.GenderType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterForm {

    @NotEmpty(message = "{register.form.email.not.empty}")
    @Email(message = "{register.form.email.format.invalid}")
    private String email;

    @NotEmpty(message = "{register.form.pwd.not.empty}")
    private String pwd;

    @NotEmpty(message = "{register.form.checkPwd.not.empty}")
    private String checkPwd;

    @NotEmpty(message = "{register.form.username.not.empty}")
    private String username;

    private String grade;

    private GenderType gender;
}
