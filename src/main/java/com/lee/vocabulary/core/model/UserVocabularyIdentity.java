package com.lee.vocabulary.core.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserVocabularyIdentity implements Serializable {

    private static final long serialVersionUID = 4533644516609341262L;

    private Integer userId;

    private Integer vocabularyId;
}
