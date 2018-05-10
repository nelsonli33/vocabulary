package com.lee.vocabulary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lee.vocabulary.entity.Vocabulary;

public interface VocabularyMapper {

	@Select("SELECT chineseword FROM words")
	List<String> getAllChinesewords();
	
	@Select("SELECT englishword FROM words WHERE chineseword = #{chineseword}")
	String getEnglishwordFromChineseword(String chineseword);
}
