package com.lee.vocabulary.facades.converters;

import com.lee.vocabulary.core.data.VocabularyData;
import com.lee.vocabulary.core.model.VocabularyModel;
import org.springframework.stereotype.Component;

@Component(value = "vocabularyConverter")
public class DefaultVocabularyConverter implements GenericConverter<VocabularyModel, VocabularyData> {

    @Override
    public VocabularyData convert(VocabularyModel source) {
        VocabularyData target = new VocabularyData();
        if (source != null) {
            target.setId(source.getId());
            target.setEnglishWord(source.getEnglishWord());
            target.setChineseWord(source.getChineseWord());
            target.setRightAmount(source.getRightAmount());
            target.setWrongAmount(source.getWrongAmount());
        }
        return target;
    }
}
