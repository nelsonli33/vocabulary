package com.lee.vocabulary.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String vocabulary(@RequestParam("username") String username, 
							HttpSession httpSession,Model model) {
		if(username != null) {
			model.addAttribute("username", username);
			httpSession.setAttribute("username", username);
		} else {
			username = (String) httpSession.getAttribute("username");
		}
		
		// 產生一個隨機單字
		Vocabulary vocabulary = vocabularyService.getRandomVacabulary();
		String chieseword = vocabulary.getChineseword();
		String englishword = vocabulary.getEnglishword();
		
		System.out.println(chieseword);
		model.addAttribute("chineseword", chieseword);
		model.addAttribute("englishword_len", englishword.length());
		
		return "vocabulary-test";
	}
	
	@GetMapping("/vocabulary")
	public String getVocabulary(Model model) {
	
		// 產生一個隨機單字
		Vocabulary vocabulary = vocabularyService.getRandomVacabulary();
		String chieseword = vocabulary.getChineseword();
		String englishword = vocabulary.getEnglishword();
		
		System.out.println(chieseword);
		System.out.println(englishword);
		model.addAttribute("chineseword", chieseword);
		model.addAttribute("englishword_len", englishword.length());
		return "vocabulary-test";
	}
}
