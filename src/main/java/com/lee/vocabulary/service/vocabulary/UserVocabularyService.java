package com.lee.vocabulary.service.vocabulary;

import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.core.model.UserVocabularyModel;
import com.lee.vocabulary.core.model.VocabularyModel;

import java.util.List;

public interface UserVocabularyService {

    void saveUserVocabulary(UserModel userModel, VocabularyModel vocabularyModel, int rightAmount, int wrongAmount);

    UserVocabularyModel getUserVocabularyForUserAndVocabualry(UserModel userModel, VocabularyModel vocabularyModel);

    List<UserVocabularyModel> getUserVocabularyForUser(UserModel userModel);
}
