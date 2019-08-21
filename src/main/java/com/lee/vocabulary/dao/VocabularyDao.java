package com.lee.vocabulary.dao;


import com.lee.vocabulary.core.model.VocabularyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VocabularyDao extends JpaRepository<VocabularyModel, Integer> {

    @Query(value = "SELECT * FROM vocabulary v ORDER BY rand() LIMIT 4", nativeQuery = true)
    List<VocabularyModel> findRandomFourVocabulary();

    VocabularyModel findVocabularyByEnglishWord(String word);

    List<VocabularyModel> findTop10ByOrderByWrongAmountDesc();

    @Transactional
    @Modifying
    @Query(value = "UPDATE vocabulary v SET v.right_amount = v.right_amount + 1 WHERE v.id = :id", nativeQuery = true)
    void incrOneRightAmountForVocabulary(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE vocabulary v SET v.wrong_amount = v.wrong_amount + 1 WHERE v.id = :id", nativeQuery = true)
    void incrOneWrongAmountForVocabulary(Integer id);
}
