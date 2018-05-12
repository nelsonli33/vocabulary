package com.lee.vocabulary.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lee.vocabulary.entity.Vocabulary;
import com.lee.vocabulary.service.VocabularyService;;

@Controller
public class VocabularyController {

	@Autowired
	private VocabularyService vocabularyService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@PostMapping("/vocabulary")
	public String handleURL(@RequestParam(required=false,name="options") String testType) {
		
		if(testType.equals("write-eng")) {			
	        return "forward:/vocabulary/write-eng";
		} else if(testType.equals("choose-chi")) {
			return "forward:/vocabulary/choose-chi";
		}
		return "index";
	}
	
	
	
	@PostMapping("/vocabulary/write-eng")
	public String vocabulary_write_english(
			@RequestParam(required=false,name="username") String username,
			@RequestParam(required=false,name="question") String question,
			@RequestParam(required=false,name="answer") String answer,
			HttpSession httpSession,Model model) {
		
		// 判斷使用者第一次登入加入 cookie，否則直接取用session值
		if(username != null) {
			model.addAttribute("username", username);
			httpSession.setAttribute("username", username);
		} else {
			username = (String) httpSession.getAttribute("username");
		}
		
		// 判斷使用者回答是否正確 正確增加單字正確次數;反之增加單字錯誤次數
		if(question != null && answer != null) {
			
			Vocabulary theVocabulary = vocabularyService.getVacabularyFromEnglish(question);
			
			if(question.equalsIgnoreCase(answer)) {
				vocabularyService.addVababularyOneRightNum(theVocabulary.getId());
			} else {
				vocabularyService.addVababularyOneWrongNum(theVocabulary.getId());
			}
		}
		
		// 產生一個隨機單字
		Vocabulary vocabulary = vocabularyService.getRandomVacabulary();
		String chieseword = vocabulary.getChineseword();
		String englishword = vocabulary.getEnglishword();
		
		System.out.println(chieseword);
		System.out.println(englishword);
		
		model.addAttribute("chineseword", chieseword);
		model.addAttribute("englishword", englishword);
		model.addAttribute("englishword_len", englishword.length());
		
		return "vocabulary-write-eng";
	}
	
	
	@PostMapping("/vocabulary/choose-chi")
	public String vocabulary_choose_chinese() {
		return "vocabulary-choose-chi";
	}
		
	

}
