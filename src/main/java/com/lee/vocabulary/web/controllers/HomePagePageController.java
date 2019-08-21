package com.lee.vocabulary.web.controllers;


import com.lee.vocabulary.web.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomePagePageController extends AbstractPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        setUpPageTitle(model, "首頁");
        return ControllerConstants.Pages.HomePage;
    }
}
