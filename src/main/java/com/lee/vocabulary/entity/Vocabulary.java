package com.lee.vocabulary.entity;

public class Vocabulary {
	private int id;
	private String englishword;
	private String chineseword;
	private String right_num;
	private String wrong_num;
	
	public Vocabulary () {
		
	}
	
	public Vocabulary(String englishword, String chineseword, String right_num, String wrong_num) {
		super();
		this.englishword = englishword;
		this.chineseword = chineseword;
		this.right_num = right_num;
		this.wrong_num = wrong_num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnglishword() {
		return englishword;
	}

	public void setEnglishword(String englishword) {
		this.englishword = englishword;
	}

	public String getChineseword() {
		return chineseword;
	}

	public void setChineseword(String chineseword) {
		this.chineseword = chineseword;
	}

	public String getRight_num() {
		return right_num;
	}

	public void setRight_num(String right_num) {
		this.right_num = right_num;
	}

	public String getWrong_num() {
		return wrong_num;
	}

	public void setWrong_num(String wrong_num) {
		this.wrong_num = wrong_num;
	}

	
	@Override
	public String toString() {
		return "Vocabulary [id=" + id + ", englishword=" + englishword + ", chineseword=" + chineseword + ", right_num="
				+ right_num + ", wrong_num=" + wrong_num + "]";
	}
	
	
}
