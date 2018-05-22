package com.lee.vocabulary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lee.vocabulary.entity.UserVocabulary;

public interface UserVocabularyMapper {

	@Select("SELECT w.englishword, w.chineseword, uw.right_num, uw.wrong_num  FROM users_words uw \n"
			+ "INNER JOIN words w ON uw.word_id = w.id\n" + "WHERE uw.user_id = #{userId}")
	List<Map<String, Object>> getUserVocabulary(int userId);

	@Select("SELECT * FROM users_words WHERE word_id = #{vocabularyId} AND user_id = #{userId}")
	UserVocabulary getUserVocabularyById(@Param("vocabularyId") int vocabularyId, @Param("userId") int userId);

	@Select("SELECT EXISTS (SELECT 1 FROM users_words WHERE word_id = #{vocabularyId} AND user_id = #{userId})")
	boolean checkUserVocabularyExists(@Param("vocabularyId") int vocabularyId, @Param("userId") int userId);

	@Insert("INSERT INTO users_words(word_id,user_id,right_num,wrong_num) "
			+ "VALUES (#{word_id}, #{user_id}, #{right_num}, #{wrong_num})")
	void insertUserVocabulary(UserVocabulary userVocabulary);

	@Update("UPDATE users_words uw SET uw.right_num = #{right_num}, uw.wrong_num = #{wrong_num} "
			+ "WHERE uw.word_id = #{vocabularyId} and uw.user_id = #{userId}")
	void updateUserVocabulary(@Param("right_num") int right_num, @Param("wrong_num") int wrong_num,
			@Param("vocabularyId") int vocabularyId, @Param("userId") int userId);
}
