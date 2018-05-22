package com.lee.vocabulary.entity;

public class UserVocabulary {
	private int word_id;
	private int user_id;
	private int right_num;
	private int wrong_num;

	public UserVocabulary() {

	}

	public UserVocabulary(int word_id, int user_id, int right_num, int wrong_num) {
		super();
		this.word_id = word_id;
		this.user_id = user_id;
		this.right_num = right_num;
		this.wrong_num = wrong_num;
	}

	public int getWord_id() {
		return word_id;
	}

	public void setWord_id(int word_id) {
		this.word_id = word_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRight_num() {
		return right_num;
	}

	public void setRight_num(int right_num) {
		this.right_num = right_num;
	}

	public int getWrong_num() {
		return wrong_num;
	}

	public void setWrong_num(int wrong_num) {
		this.wrong_num = wrong_num;
	}

	@Override
	public String toString() {
		return "UserVocabulary [word_id=" + word_id + ", user_id=" + user_id + ", right_num=" + right_num
				+ ", wrong_num=" + wrong_num + "]";
	}

}
