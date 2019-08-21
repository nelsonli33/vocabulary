package com.lee.vocabulary.core.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVocabularyData implements Serializable {
    private static final long serialVersionUID = 2790812889866925688L;
    private VocabularyData vocabularyData;
    private int rightAmount;
    private int wrongAmount;
}
