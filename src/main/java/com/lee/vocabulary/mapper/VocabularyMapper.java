package com.lee.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lee.vocabulary.entity.Vocabulary;

public interface VocabularyMapper {

	@Select("select * from words order by rand() limit 1")
	Vocabulary getRandomVocabulary();

	@Select("select * from words order by rand() limit 4")
	List<Vocabulary> getRandomFourVocabulary();

	@Select("SELECT * FROM words WHERE englishword = #{englishword}")
	Vocabulary getVacabularyFromEnglish(String englishword);

	@Select("SELECT * FROM words ORDER BY wrong_num DESC limit 10")
	List<Vocabulary> getTopTenErrorAnswerVocabulary();

	@Update("UPDATE words SET right_num = right_num + 1 WHERE id = #{id}")
	void addVacabularyOneRightNum(int id);

	@Update("UPDATE words SET wrong_num = wrong_num + 1 WHERE id = #{id}")
	void addVacabularyOneWrongNum(int id);
}
