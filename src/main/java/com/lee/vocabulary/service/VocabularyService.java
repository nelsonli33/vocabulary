package com.lee.vocabulary.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.vocabulary.entity.Vocabulary;
import com.lee.vocabulary.mapper.VocabularyMapper;

@Service
public class VocabularyService {
	
	@Autowired
	private VocabularyMapper vocabularyMapper;
	
	// 亂數取得一個單字
	public Vocabulary getRandomVacabulary() {
		List<Vocabulary> vocabulary_list = vocabularyMapper.getAllVocabulary();
		Random random = new Random();
		int randomIndex = random.nextInt(vocabulary_list.size());
		Vocabulary vocabulary = vocabulary_list.get(randomIndex);
		return vocabulary;
	}
	
	
	
}
