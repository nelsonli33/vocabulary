package com.lee.vocabulary.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.vocabulary.entity.Vocabulary;
import com.lee.vocabulary.mapper.VocabularyMapper;

@Service
public class VocabularyService {
	

	@Autowired
	private VocabularyMapper vocabularyMapper;
	
	 // 亂數取得一個單字
	public Vocabulary getRandomVocabulary() {
			List<Vocabulary> vocabulary_list = vocabularyMapper.getAllVocabulary();
			Random random = new Random();
			int randomIndex = random.nextInt(vocabulary_list.size());
			Vocabulary vocabulary = vocabulary_list.get(randomIndex);
			return vocabulary;
	}

		
	public Vocabulary getVacabularyFromEnglish(String englishword) {
			 return vocabularyMapper.getVacabularyFromEnglish(englishword);
	}


	public void addVababularyOneRightNum(int id) {
			vocabularyMapper.addVacabularyOneRightNum(id);
	}
		
	public void addVababularyOneWrongNum(int id) {
			vocabularyMapper.addVacabularyOneWrongNum(id);
	}
		
		
	// 判斷使用者回答是否正確 正確增加單字正確次數;反之增加單字錯誤次數
	public void checkUserAnswer(String question, String userAnswer) {
		
		Vocabulary theVocabulary = getVacabularyFromEnglish(question);
			
		if(question.equalsIgnoreCase(userAnswer)) {
			addVababularyOneRightNum(theVocabulary.getId());
		} else {
			addVababularyOneWrongNum(theVocabulary.getId());
		}
	}
	
	
	public Set<Vocabulary> getRandomFourVocabulary(){
		
		Set<Vocabulary> vocabulary_set = new HashSet<Vocabulary>();
		
		while(vocabulary_set.size() < 4 ) {
			Vocabulary vocabulary = getRandomVocabulary();
			vocabulary_set.add(vocabulary);
		}
		return vocabulary_set;
	}

	
	
}
