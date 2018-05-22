package com.lee.vocabulary.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lee.vocabulary.entity.User;

public interface UserMapper {

	// 使用Account查詢會員
	@Select("SELECT * from users WHERE account = #{account}")
	User queryUserByAccount(String account);

	// 兩個參數時必須使用@Param為參數命名
	@Select("SELECT * FROM users WHERE account = #{account} AND password = #{password}")
	User queryUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

	// 新增會員
	@Insert("INSERT INTO users(username,account,email,password,sex,grade) VALUES (#{username},#{account},#{email},#{password},#{sex},#{grade})")
	public void insertNewUser(User user);
}
