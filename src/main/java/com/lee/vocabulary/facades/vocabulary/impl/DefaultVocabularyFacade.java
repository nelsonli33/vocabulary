package com.lee.vocabulary.facades.vocabulary.impl;


import com.lee.vocabulary.core.data.VocabularyData;
import com.lee.vocabulary.core.model.VocabularyModel;
import com.lee.vocabulary.facades.converters.GenericConverter;
import com.lee.vocabulary.facades.vocabulary.VocabularyFacade;
import com.lee.vocabulary.service.vocabulary.VocabularyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "vocabularyFacade")
public class DefaultVocabularyFacade implements VocabularyFacade {

    @Resource(name = "vocabularyService")
    private VocabularyService vocabularyService;

    @Resource(name = "vocabularyConverter")
    private GenericConverter<VocabularyModel, VocabularyData> vocabularyConverter;


    @Override
    public VocabularyData getRandomVocabulary() {
        return vocabularyConverter.convert(vocabularyService.getRandomVocabulary());
    }

    @Override
    public List<VocabularyData> getRandomFourVocabulary() {
        return vocabularyConverter.convertAll(vocabularyService.getRandomFourVocabulary());
    }

    @Override
    public VocabularyData getVacabularyForEnglishWord(String word) {
        if (StringUtils.isEmpty(word)) {
            return null;
        }
        return vocabularyConverter.convert(vocabularyService.getVacabularyForEnglishWord(word));
    }

    @Override
    public List<VocabularyData> getTopTenWrongAnswerVocabulary() {
        return vocabularyConverter.convertAll(vocabularyService.getTopTenWrongAnswerVocabulary());
    }

    @Override
    public void increVocabularyRightOrWrongAmount(String question, String answer) {
        VocabularyModel vocabulary = vocabularyService.getVacabularyForEnglishWord(question);
        if (vocabulary != null) {
            if (question.equalsIgnoreCase(answer)) {
                vocabularyService.incrOneRightAmountForVocabulary(vocabulary.getId());
            } else {
                vocabularyService.incrOneRightAmountForVocabulary(vocabulary.getId());
            }
        }

    }
}
