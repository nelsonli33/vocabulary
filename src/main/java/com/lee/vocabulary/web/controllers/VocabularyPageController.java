package com.lee.vocabulary.web.controllers;

import com.lee.vocabulary.core.data.AnswerData;
import com.lee.vocabulary.core.data.VocabularyData;
import com.lee.vocabulary.facades.vocabulary.UserVocabularyFacade;
import com.lee.vocabulary.facades.vocabulary.VocabularyFacade;
import com.lee.vocabulary.web.ControllerConstants;
import com.lee.vocabulary.web.form.GameForm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

;

@Controller
@RequestMapping(value = "/vocabulary")
public class VocabularyPageController extends AbstractPageController {

    private static final String FORWARD_WRITE_ENG_URL = FORWARD_PREFIX + "/vocabulary/write-english";
    private static final String FORWARD_CHOOSE_CHI_URL = FORWARD_PREFIX + "/vocabulary//choose-chinese";
    private static Logger LOG = LoggerFactory.getLogger(VocabularyPageController.class);

    @Resource(name = "vocabularyFacade")
    private VocabularyFacade vocabularyFacade;

    @Resource(name = "userVocabularyFacade")
    private UserVocabularyFacade userVocabularyFacade;


    @RequestMapping(method = RequestMethod.POST)
    public String vocabularyPage(GameForm form) {
        String mode = form.getGameMode();
        if ("mode1".equals(mode)) {
            return FORWARD_WRITE_ENG_URL;
        } else if ("mode2".equals(mode)) {
            return FORWARD_CHOOSE_CHI_URL;
        }
        return ControllerConstants.Pages.HomePage;
    }


    @RequestMapping(value = "/write-english", method = RequestMethod.POST)
    public String vocabularyWriteEnglishPage(GameForm form, Model model) {
        String username = form.getUsername();
        if (StringUtils.isEmpty(username)) {
            username = "訪客";
        }
        model.addAttribute("username", username);
        setUpPageTitle(model, "猜單字測驗");
        return ControllerConstants.Pages.Vocabulary.VocabularyWriteEngPage;
    }


    @RequestMapping(value = "/getRandomVocabulary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public VocabularyData getRandomVocabulary() {
        return vocabularyFacade.getRandomVocabulary();
    }


    @RequestMapping(value = "/getRandomFourVocabulary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<VocabularyData> getRandomFourVocabulary() {
        return vocabularyFacade.getRandomFourVocabulary();
    }


    // 判斷使用者的回答 增加資料庫中的單字對錯次數
    @RequestMapping(value = "/increVocabularyRightOrWrongAmount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity increVocabularyRightOrWrongAmount(@RequestBody AnswerData answerData) {
        String question = answerData.getQuestion();
        String answer = answerData.getAnswer();

        if (StringUtils.isEmpty(question)) {
            throw new IllegalArgumentException("question can't be empty.");
        }

        vocabularyFacade.increVocabularyRightOrWrongAmount(question, answer);

        String uid = getUserFacade().getCurrentUserUid();
        if (StringUtils.isNotEmpty(uid)) {
            userVocabularyFacade.recordUserVocabulary(uid, question, answer);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/choose-chinese", method = RequestMethod.POST)
    public String vocabularyChooseChinesePage(GameForm form, Model model) {
        String username = form.getUsername();
        if (StringUtils.isEmpty(username)) {
            username = "訪客";
        }
        model.addAttribute("username", username);
        setUpPageTitle(model, "英文翻中文選擇題");
        return ControllerConstants.Pages.Vocabulary.VocabularyChooseChiPage;
    }

    @RequestMapping(value = "/wrong-answers", method = RequestMethod.GET)
    public String vocabularyWrongAnswerPage(Model model) {
        List<VocabularyData> vocabularies = vocabularyFacade.getTopTenWrongAnswerVocabulary();
        model.addAttribute("vocabularies", vocabularies);
        setUpPageTitle(model, "常見錯誤單字排行榜");
        return ControllerConstants.Pages.Vocabulary.VocabularyWrongAnswerPage;
    }

}
