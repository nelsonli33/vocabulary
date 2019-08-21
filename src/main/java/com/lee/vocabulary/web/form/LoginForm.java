package com.lee.vocabulary.web.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginForm {

    @NotNull(message = "{general.required}")
    private String j_username;

    @NotNull(message = "{general.required}")
    private String j_password;

}
