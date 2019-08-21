package com.lee.vocabulary.service.vocabulary.impl;

import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.core.model.UserVocabularyModel;
import com.lee.vocabulary.core.model.VocabularyModel;
import com.lee.vocabulary.dao.UserVocabularyDao;
import com.lee.vocabulary.service.vocabulary.UserVocabularyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userVocabularyService")
public class DefaultUserVocabularyService implements UserVocabularyService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserVocabularyService.class);

    @Resource(name = "userVocabularyDao")
    private UserVocabularyDao userVocabularyDao;

    @Override
    public void saveUserVocabulary(UserModel userModel, VocabularyModel vocabulary, int rightAmount, int wrongAmount) {
        UserVocabularyModel userVocabulary = new UserVocabularyModel();
        userVocabulary.setUser(userModel);
        userVocabulary.setVocabulary(vocabulary);
        userVocabulary.setRightAmount(rightAmount);
        userVocabulary.setWrongAmount(wrongAmount);
        userVocabularyDao.save(userVocabulary);
    }

    @Override
    public List<UserVocabularyModel> getUserVocabularyForUser(UserModel userModel) {
        return userVocabularyDao.findUserVocabularyModelsByUser(userModel);
    }

    @Override
    public UserVocabularyModel getUserVocabularyForUserAndVocabualry(UserModel userModel, VocabularyModel vocabulary) {
        return userVocabularyDao.findUserVocabularyModelByUserAndVocabulary(userModel, vocabulary);
    }


}
