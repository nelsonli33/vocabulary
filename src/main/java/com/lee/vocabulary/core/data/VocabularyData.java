package com.lee.vocabulary.core.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class VocabularyData implements Serializable {
    private static final long serialVersionUID = 2530227516614678967L;
    private int id;
    private String englishWord;
    private String chineseWord;
    private int rightAmount;
    private int wrongAmount;
}
