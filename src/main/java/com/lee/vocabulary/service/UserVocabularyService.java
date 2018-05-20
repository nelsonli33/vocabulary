package com.lee.vocabulary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.vocabulary.entity.UserVocabulary;
import com.lee.vocabulary.mapper.UserVocabularyMapper;

@Service
public class UserVocabularyService {
	
	@Autowired
	private UserVocabularyMapper userVocabularyMapper;
	
	public void insertUserVocabulary(UserVocabulary userVocabulary) {
		userVocabularyMapper.insertUserVocabulary(userVocabulary);
	}
	
	public List<Map<String,Object>> getUserVocabulary(int userId){
		return userVocabularyMapper.getUserVocabulary(userId);
	}
}
