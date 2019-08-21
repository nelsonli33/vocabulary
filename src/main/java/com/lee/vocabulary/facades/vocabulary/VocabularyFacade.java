package com.lee.vocabulary.facades.vocabulary;

import com.lee.vocabulary.core.data.VocabularyData;

import java.util.List;

public interface VocabularyFacade {

    VocabularyData getRandomVocabulary();

    List<VocabularyData> getRandomFourVocabulary();

    VocabularyData getVacabularyForEnglishWord(String word);

    List<VocabularyData> getTopTenWrongAnswerVocabulary();

    void increVocabularyRightOrWrongAmount(String question, String answer);
}
