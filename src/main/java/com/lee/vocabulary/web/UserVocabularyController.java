package com.lee.vocabulary.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.vocabulary.entity.User;
import com.lee.vocabulary.entity.UserVocabulary;
import com.lee.vocabulary.entity.Vocabulary;
import com.lee.vocabulary.service.UserService;
import com.lee.vocabulary.service.UserVocabularyService;
import com.lee.vocabulary.service.VocabularyService;

@Controller
public class UserVocabularyController {
	
	@Autowired
	private UserVocabularyService userVocabularyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VocabularyService vocabularyService;
	
	/*
	 * 登入後顯示會員統計資訊
	 */
	@GetMapping("/user/vocabulary/statistic")
	public String userVocabularyStatistic() {
		return "user_vocabulary_statistic";
	}
	
	/*
	 * 取得 account 和 englishword ID注入 UserVocabulary物件寫入資料庫
	 */
	@RequestMapping(value="/user/vocabulary/api/insertUserVocabulary", method=RequestMethod.POST)
	@ResponseBody
	public void insertUserVocabulary(@RequestBody Map<String,String> jsonStr) {
		
		String account = jsonStr.get("account");
		String englishword = jsonStr.get("question");
		String answer = jsonStr.get("answer");
		
		User user = userService.queryUserByAccount(account);
		Vocabulary vocabulary = vocabularyService.getVacabularyFromEnglish(englishword);
		
		UserVocabulary userVocabulary = new UserVocabulary();
		userVocabulary.setUser_id(user.getId());
		userVocabulary.setWord_id(vocabulary.getId());
		
		if(englishword.toUpperCase().equals(answer.toUpperCase())) {
			userVocabulary.setRight_num(1);
		} else {
			userVocabulary.setWrong_num(1);
		}
		
		System.out.println(userVocabulary);
		
		userVocabularyService.insertUserVocabulary(userVocabulary);
	}
	
	/*
	 * System.out.println(userVocabularyService.getUserVocabulary(1));
	 */
	@RequestMapping(value="/user/vocabulary/api/getUserVocabulary", method=RequestMethod.POST , produces="application/json")
	@ResponseBody
	public List<Map<String,Object>> getUserVocabulary(@RequestBody Map<String,String> jsonStr) {
		String account = jsonStr.get("account");
		User user = userService.queryUserByAccount(account);
		return userVocabularyService.getUserVocabulary(user.getId());
	}
	
	
	
}
