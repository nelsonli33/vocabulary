package com.lee.vocabulary.service.vocabulary.impl;

import com.lee.vocabulary.core.model.VocabularyModel;
import com.lee.vocabulary.dao.VocabularyDao;
import com.lee.vocabulary.service.vocabulary.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "vocabularyService")
public class DefaultVocabularyService implements VocabularyService {

    @Autowired
    private VocabularyDao vocabularyDao;

    @Override
    public VocabularyModel getRandomVocabulary() {
        Long count = vocabularyDao.count();
        int index = (int) (Math.random() * count);

        Page<VocabularyModel> vocabularyPage = vocabularyDao.findAll(PageRequest.of(index, 1));
        if (vocabularyPage.hasContent()) {
            return vocabularyPage.getContent().get(0);
        }
        return null;
    }

    @Override
    public List<VocabularyModel> getRandomFourVocabulary() {
        return vocabularyDao.findRandomFourVocabulary();
    }

    @Override
    public VocabularyModel getVacabularyForEnglishWord(String word) {
        return vocabularyDao.findVocabularyByEnglishWord(word);
    }

    @Override
    public void incrOneRightAmountForVocabulary(int id) {
        vocabularyDao.incrOneRightAmountForVocabulary(id);
    }

    @Override
    public void incrOneWrongAmountForVocabulary(int id) {
        vocabularyDao.incrOneWrongAmountForVocabulary(id);
    }

    @Override
    public List<VocabularyModel> getTopTenWrongAnswerVocabulary() {
        return vocabularyDao.findTop10ByOrderByWrongAmountDesc();
    }


}
