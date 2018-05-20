package com.lee.vocabulary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.vocabulary.entity.User;
import com.lee.vocabulary.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User queryUserByAccount(String account) {
		return userMapper.queryUserByAccount(account);
	}
	
	public User queryUserByAccountAndPassword(String account, String password) {
		return userMapper.queryUserByAccountAndPassword(account,password);
	}
	
	public void insertNewUser(User user) {
		userMapper.insertNewUser(user);
	}
	
	
}
