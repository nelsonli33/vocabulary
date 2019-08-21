package com.lee.vocabulary.web.controllers;

import com.lee.vocabulary.core.data.RegisterData;
import com.lee.vocabulary.core.enums.GenderType;
import com.lee.vocabulary.core.security.AutoLoginStrategy;
import com.lee.vocabulary.service.exceptions.DuplicateUidException;
import com.lee.vocabulary.web.ControllerConstants;
import com.lee.vocabulary.web.common.SelectOption;
import com.lee.vocabulary.web.form.RegisterForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/register")
public class RegisterPageController extends AbstractPageController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterPageController.class);

    @Resource(name = "registrationValidator")
    private Validator registrationValidator;

    @Resource(name = "autoLoginStrategy")
    private AutoLoginStrategy autoLoginStrategy;

    @ModelAttribute("genders")
    public List<SelectOption> getGender() {
        List<SelectOption> genders = new ArrayList<>();
        genders.add(new SelectOption(GenderType.MALE.getCode(), "男"));
        genders.add(new SelectOption(GenderType.FEMALE.getCode(), "女"));
        return genders;
    }

    @ModelAttribute("grades")
    public List<SelectOption> getGrade() {
        List<SelectOption> grades = new ArrayList<>();
        grades.add(new SelectOption("高三", "高三"));
        grades.add(new SelectOption("高二", "高二"));
        grades.add(new SelectOption("高一", "高一"));
        grades.add(new SelectOption("國三", "國三"));
        grades.add(new SelectOption("國二", "國二"));
        grades.add(new SelectOption("國一", "國一"));
        grades.add(new SelectOption("其他", "其他"));
        return grades;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String register(Model model) {
        return getDefaultRegistrationPage(model);
    }


    @RequestMapping(method = RequestMethod.POST)
    public String register(Model model, @Valid RegisterForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        registrationValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerForm", form);
            return handleRegistrationFormError(model);
        }

        RegisterData data = new RegisterData();
        data.setUid(form.getEmail().toLowerCase());
        data.setUsername(form.getUsername());
        data.setPassword(form.getPwd());
        data.setGrade(form.getGrade());
        data.setGender(form.getGender());

        try {
            getUserFacade().register(data);
            autoLoginStrategy.login(form.getEmail().toLowerCase(), form.getPwd(), request);
        } catch (DuplicateUidException ex) {
            LOG.warn("registration failed: ", ex);
        }
        return REDIRECT_PREFIX + "/";

    }

    /*
     * 檢查會員帳號是否已被註冊
     */
    @RequestMapping(value = "/checkIsAccountExist", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkIsAccountExist(@RequestBody Map<String, String> data) {
        String uid = data.get("uid");
        return getUserFacade().isAccountExists(uid);
    }


    protected String getDefaultRegistrationPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        setUpPageTitle(model, "註冊會員");
        return getView();
    }

    protected String handleRegistrationFormError(Model model) {
        setUpPageTitle(model, "註冊會員");
        return getView();
    }

    protected String getView() {
        return ControllerConstants.Pages.User.UserRegisterPage;
    }


}
