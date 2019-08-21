package com.lee.vocabulary.core.model;

import com.lee.vocabulary.core.enums.GenderType;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserModel extends Item {

    @OneToMany(mappedBy = "user")
    private Set<UserVocabularyModel> userVocabularyModel = new HashSet<>();

    @NaturalId
    private String uid;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String grade;
}