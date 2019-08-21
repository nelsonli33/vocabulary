package com.lee.vocabulary.facades.vocabulary;

import com.lee.vocabulary.core.data.UserVocabularyData;

import java.util.List;

public interface UserVocabularyFacade {
    List<UserVocabularyData> getUserVocabularyForUser(String uid);

    void recordUserVocabulary(String uid, String question, String answer);
}
