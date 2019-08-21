package com.lee.vocabulary.dao;


import com.lee.vocabulary.core.model.UserModel;
import com.lee.vocabulary.core.model.UserVocabularyIdentity;
import com.lee.vocabulary.core.model.UserVocabularyModel;
import com.lee.vocabulary.core.model.VocabularyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVocabularyDao extends JpaRepository<UserVocabularyModel, UserVocabularyIdentity> {

    List<UserVocabularyModel> findUserVocabularyModelsByUser(UserModel user);

    UserVocabularyModel findUserVocabularyModelByUserAndVocabulary(UserModel user, VocabularyModel vocabulary);
}
