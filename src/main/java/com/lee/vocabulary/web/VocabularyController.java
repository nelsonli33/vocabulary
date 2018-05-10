package com.lee.vocabulary.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		String chieseword = vocabularyService.getChineseWord();
		model.addAttribute("chineseword", chieseword);
		
		return "vocabulary-test";
	}
	
	@GetMapping("/vocabulary")
	public String getVocabulary(Model model) {
	
		// 產生一個隨機單字
		String chieseword = vocabularyService.getChineseWord();
		System.out.println(chieseword);
		
		model.addAttribute("chineseword", chieseword);
		return "vocabulary-test";
	}
}
