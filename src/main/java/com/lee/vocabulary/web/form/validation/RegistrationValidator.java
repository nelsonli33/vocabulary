package com.lee.vocabulary.web.form.validation;


import com.lee.vocabulary.web.form.RegisterForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component(value = "registrationValidator")
public class RegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterForm registerForm = (RegisterForm) o;
        String pwd = registerForm.getPwd();
        String checkPwd = registerForm.getCheckPwd();

        comparePasswords(errors, pwd, checkPwd);
    }

    private void comparePasswords(Errors errors, String pwd, String checkPwd) {
        if (StringUtils.isNotEmpty(pwd) && StringUtils.isNotEmpty(checkPwd) && !StringUtils.equals(pwd, checkPwd)) {
            errors.rejectValue("checkPwd", "register.form.checkPwd.invalid");
        }
    }
}
