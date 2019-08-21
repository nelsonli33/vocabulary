package com.lee.vocabulary.web.controllers;

import com.lee.vocabulary.web.ControllerConstants;
import com.lee.vocabulary.web.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginPageController extends AbstractPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        return getDefaultLoginPage(model);
    }

    public String getDefaultLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        setUpPageTitle(model, "登入");
        return ControllerConstants.Pages.User.UserLoginPage;
    }
}
