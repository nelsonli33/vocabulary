package com.lee.vocabulary.facades.vocabulary.impl;

import com.lee.vocabulary.core.data.UserVocabularyData;
import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.core.model.UserVocabularyModel;
import com.lee.vocabulary.core.model.VocabularyModel;
import com.lee.vocabulary.facades.converters.GenericConverter;
import com.lee.vocabulary.facades.vocabulary.UserVocabularyFacade;
import com.lee.vocabulary.service.user.UserService;
import com.lee.vocabulary.service.vocabulary.UserVocabularyService;
import com.lee.vocabulary.service.vocabulary.VocabularyService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Component(value = "userVocabularyFacade")
public class DefaultUserVocabularyFacade implements UserVocabularyFacade {

    @Resource(name = "userVocabularyService")
    private UserVocabularyService userVocabularyService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "vocabularyService")
    private VocabularyService vocabularyService;

    @Resource(name = "userVocabularyConverter")
    private GenericConverter<UserVocabularyModel, UserVocabularyData> userVocabularyConverter;

    @Override
    public List<UserVocabularyData> getUserVocabularyForUser(String uid) {
        UserModel userModel = userService.getUserForUid(uid);
        if (userModel != null) {
            return userVocabularyConverter.convertAll(userVocabularyService.getUserVocabularyForUser(userModel));
        }
        return Collections.emptyList();
    }


    @Override
    public void recordUserVocabulary(String uid, String question, String answer) {
        UserModel userModel = userService.getUserForUid(uid);
        VocabularyModel vocabularyModel = vocabularyService.getVacabularyForEnglishWord(question);
        Assert.notNull(userModel, "userModel cannot be null.");
        Assert.notNull(userModel, "vocabularyModel cannot be null.");
        UserVocabularyModel userVocabularyModel = userVocabularyService.getUserVocabularyForUserAndVocabualry(userModel, vocabularyModel);

        if (question.equalsIgnoreCase(answer)) {
            if (userVocabularyModel == null) {
                userVocabularyService.saveUserVocabulary(userModel, vocabularyModel, 1, 0);
            } else {
                userVocabularyService.saveUserVocabulary(userModel, vocabularyModel, userVocabularyModel.getRightAmount() + 1, userVocabularyModel.getWrongAmount());
            }
        } else {
            if (userVocabularyModel == null) {
                userVocabularyService.saveUserVocabulary(userModel, vocabularyModel, 0, 1);
            } else {
                userVocabularyService.saveUserVocabulary(userModel, vocabularyModel, userVocabularyModel.getRightAmount(), userVocabularyModel.getWrongAmount() + 1);
            }
        }
    }

}
