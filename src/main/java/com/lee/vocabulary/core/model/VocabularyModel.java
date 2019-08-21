package com.lee.vocabulary.core.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class VocabularyModel extends Item {

    @OneToMany(mappedBy = "vocabulary")
    private Set<UserVocabularyModel> userVocabularyModel = new HashSet<>();

    private String englishWord;
    private String chineseWord;
    private Integer rightAmount;
    private Integer wrongAmount;
}