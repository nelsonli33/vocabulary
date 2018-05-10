package com.lee.vocabulary.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.vocabulary.mapper.VocabularyMapper;

@Service
public class VocabularyService {
	
	@Autowired
	private VocabularyMapper vocabularyMapper;
	
	//取得一個隨機中文單字
	public String getChineseWord() {
		List<String> chinesewords = vocabularyMapper.getAllChineseWords();
		Random random = new Random();
		int randomIndex = random.nextInt(chinesewords.size());
		String s = chinesewords.get(randomIndex);
		return s;
	}
}
