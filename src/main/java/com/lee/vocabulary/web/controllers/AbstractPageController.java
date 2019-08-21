package com.lee.vocabulary.web.controllers;

import com.lee.vocabulary.core.data.UserData;
import com.lee.vocabulary.facades.user.UserFacade;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

public abstract class AbstractPageController {
    public static final String REDIRECT_PREFIX = "redirect:";
    public static final String FORWARD_PREFIX = "forward:";
    public static final String PAGE_TITLE = "pageTitle";

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @ModelAttribute("user")
    public UserData getUser() {
        return getUserFacade().getCurrentUser();
    }

    protected void setUpPageTitle(Model model, String title) {
        model.addAttribute(PAGE_TITLE, title);
    }


    public UserFacade getUserFacade() {
        return userFacade;
    }
}
