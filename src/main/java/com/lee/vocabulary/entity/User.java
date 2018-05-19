package com.lee.vocabulary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 *  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sex`  char(4),
  `grade` varchar(30) ,
 */
public class User {
	
	@JsonIgnore
	private int id;
	private String username;
	private String account;
	private String email;
	private String password;
	private String sex;
	private String grade;
	
	public User() {
		
	}
	
	public User( String username,String account, String email, String password, String sex, String grade) {
		super();
		this.username = username;
		this.account = account;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.grade = grade;
	}



	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", username=" + username + ", email=" + email + ", password="
				+ password + ", sex=" + sex + ", grade=" + grade + "]";
	}
	
	
}
