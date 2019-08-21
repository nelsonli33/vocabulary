package com.lee.vocabulary.core.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserVocabularyModel {

    @EmbeddedId
    private UserVocabularyIdentity userVocabularyIdentity = new UserVocabularyIdentity();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @MapsId("vocabularyId")
    @JoinColumn(name = "vocabulary_id")
    private VocabularyModel vocabulary;

    private Integer rightAmount;
    private Integer wrongAmount;
}