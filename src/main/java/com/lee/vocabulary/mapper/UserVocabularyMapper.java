package com.lee.vocabulary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lee.vocabulary.entity.UserVocabulary;

public interface UserVocabularyMapper {
	
	
	@Insert("INSERT INTO users_words(word_id,user_id,right_num,wrong_num) "
			+ "VALUES (#{word_id}, #{user_id}, #{right_num}, #{wrong_num})")
	void insertUserVocabulary(UserVocabulary userVocabulary);
	
	
	@Select("SELECT w.englishword, w.chineseword, uw.right_num, uw.wrong_num  FROM users_words uw \n" + 
			"INNER JOIN words w ON uw.word_id = w.id\n" + 
			"WHERE uw.user_id = #{userId}")
	List<Map<String,Object>> getUserVocabulary(int userId);
}
