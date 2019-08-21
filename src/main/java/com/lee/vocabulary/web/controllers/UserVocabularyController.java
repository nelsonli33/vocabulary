package com.lee.vocabulary.web.controllers;

import com.lee.vocabulary.core.data.UserVocabularyData;
import com.lee.vocabulary.facades.vocabulary.UserVocabularyFacade;
import com.lee.vocabulary.web.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/my-account")
public class UserVocabularyController extends AbstractPageController {

    @Resource(name = "userVocabularyFacade")
    private UserVocabularyFacade userVocabularyFacade;


    /*
     * 登入後顯示會員統計資訊
     */
    @RequestMapping(value = "/vocabulary/statistic", method = RequestMethod.GET)
    public String userVocabularyStatistic(Model model) {

        String uid = getUserFacade().getCurrentUserUid();
        List<UserVocabularyData> userVocabularies = userVocabularyFacade.getUserVocabularyForUser(uid);

        int rightAmount = userVocabularies.stream().mapToInt(userVocabulary -> userVocabulary.getRightAmount()).sum();
        int wrongAmount = userVocabularies.stream().mapToInt(userVocabulary -> userVocabulary.getWrongAmount()).sum();


        model.addAttribute("userVocabularyCount", userVocabularies.size());
        model.addAttribute("rightRatio", (float) rightAmount / userVocabularies.size());
        model.addAttribute("wrongRatio", (float) wrongAmount / userVocabularies.size());
        model.addAttribute("userVocabularies", userVocabularies);
        return getDefaultUserVocabularyPage(model);
    }


    private String getDefaultUserVocabularyPage(Model model) {
        setUpPageTitle(model, "會員單字統計資訊");
        return ControllerConstants.Pages.Vocabulary.UserVocabularyStatisticPage;
    }


}
