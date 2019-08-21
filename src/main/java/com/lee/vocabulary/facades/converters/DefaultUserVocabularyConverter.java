package com.lee.vocabulary.facades.converters;

import com.lee.vocabulary.core.data.UserVocabularyData;
import com.lee.vocabulary.core.data.VocabularyData;
import com.lee.vocabulary.core.model.UserVocabularyModel;
import com.lee.vocabulary.core.model.VocabularyModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "userVocabularyConverter")
public class DefaultUserVocabularyConverter implements GenericConverter<UserVocabularyModel, UserVocabularyData> {


    @Resource(name = "vocabularyConverter")
    private GenericConverter<VocabularyModel, VocabularyData> vocabularyConverter;

    @Override
    public UserVocabularyData convert(UserVocabularyModel source) {
        UserVocabularyData target = new UserVocabularyData();
        if (source != null) {
            target.setVocabularyData(vocabularyConverter.convert(source.getVocabulary()));
            target.setRightAmount(source.getRightAmount());
            target.setWrongAmount(source.getWrongAmount());
        }
        return target;
    }
}
