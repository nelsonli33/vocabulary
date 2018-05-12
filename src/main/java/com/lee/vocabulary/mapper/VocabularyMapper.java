package com.lee.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lee.vocabulary.entity.Vocabulary;

public interface VocabularyMapper {

	@Select("SELECT * FROM words")
	List<Vocabulary> getAllVocabulary();
	
	@Select("SELECT * FROM words WHERE englishword = #{englishword}")
	Vocabulary getVacabularyFromEnglish(String englishword);
	
	@Update("UPDATE words SET right_num = right_num + 1 WHERE id = #{id}")
	void addVacabularyOneRightNum(int id);

	@Update("UPDATE words SET wrong_num = wrong_num + 1 WHERE id = #{id}")
	void addVacabularyOneWrongNum(int id);
}
