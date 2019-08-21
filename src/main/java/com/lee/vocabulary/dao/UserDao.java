package com.lee.vocabulary.dao;


import com.lee.vocabulary.core.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserModel, Integer> {
    UserModel findByUid(String uid);
}
