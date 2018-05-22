package com.lee.vocabulary.service;

import java.util.List;

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
		return vocabularyMapper.getRandomVocabulary();
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

	public List<Vocabulary> getTopTenErrorAnswerVocabulary() {
		return vocabularyMapper.getTopTenErrorAnswerVocabulary();
	}

	// 判斷使用者回答是否正確 正確增加單字正確次數;反之增加單字錯誤次數
	public void checkUserAnswer(String question, String userAnswer) {

		Vocabulary theVocabulary = getVacabularyFromEnglish(question);

		if (question.equalsIgnoreCase(userAnswer)) {
			addVababularyOneRightNum(theVocabulary.getId());
		} else {
			addVababularyOneWrongNum(theVocabulary.getId());
		}
	}

	public List<Vocabulary> getRandomFourVocabulary() {
		return vocabularyMapper.getRandomFourVocabulary();
	}

}
