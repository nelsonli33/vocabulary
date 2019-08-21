package com.lee.vocabulary.service.vocabulary;

import com.lee.vocabulary.core.model.VocabularyModel;

import java.util.List;

public interface VocabularyService {

    VocabularyModel getRandomVocabulary();

    List<VocabularyModel> getRandomFourVocabulary();

    VocabularyModel getVacabularyForEnglishWord(String word);

    void incrOneRightAmountForVocabulary(int vocabularyId);

    void incrOneWrongAmountForVocabulary(int vocabularyId);

    List<VocabularyModel> getTopTenWrongAnswerVocabulary();
}
