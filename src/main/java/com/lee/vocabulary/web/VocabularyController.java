package com.lee.vocabulary.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
		
		// 判斷使用者第一次登入加入 session 作用域，否則直接取用session值
		if(username != null) {
			model.addAttribute("username", username);
			httpSession.setAttribute("username", username);
		} else {
			username = (String) httpSession.getAttribute("username");
		}
		
		// 判斷使用者回答是否正確 正確增加單字正確次數;反之增加單字錯誤次數
		if(question != null && answer != null) {
			vocabularyService.checkUserAnswer(question,answer);
		}
		
		// 產生一個隨機單字
		Vocabulary vocabulary = vocabularyService.getRandomVocabulary();
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
	public String vocabulary_choose_chinese(
			@RequestParam(required=false,name="username") String username,
			@RequestParam(required=false,name="inlineRadioOptions") String userAnswer,
			@RequestParam(required=false,name="question") String userQuestion,
			HttpSession httpSession,
			Model model) {
		
		// 判斷使用者第一次登入加入 cookie，否則直接取用session值
		if(username != null) {
			model.addAttribute("username", username);
			httpSession.setAttribute("username", username);
		} else {
			username = (String) httpSession.getAttribute("username");			
		}
		
		// 產生四個隨機單字 放入不重複Set集合
		Set<Vocabulary> vocabulary_set = vocabularyService.getRandomFourVocabulary();
		
		
		String[] abcd = {"A","B","C","D"}; 
		System.out.println("--vocabulary_set內容--");
		int count = 0;
		for(Vocabulary v : vocabulary_set) {
			// (A) [名詞] 愉快 
			StringBuffer buffer = new StringBuffer("(");
			buffer.append(abcd[count]+") ");
			buffer.append(v.getChineseword());
			
			System.out.println(buffer.toString());
			// option_chi1
			count++;
			model.addAttribute("option_chi"+count, buffer.toString());
			model.addAttribute("option_val"+count, v.getEnglishword());
		}
		System.out.println("-------------------");
		
		
		// 選出四個選項其中一個當作題目
		Random random = new Random();
		int randomIndex = random.nextInt(vocabulary_set.size());
		
		List<Vocabulary> vocabulary_list = new ArrayList<Vocabulary>();
		for(Vocabulary v : vocabulary_set) {
			vocabulary_list.add(v);
		}
		
		//System.out.println(randomIndex+abcd[randomIndex]);
		
		Vocabulary question_voc = vocabulary_list.get(randomIndex);
		// (B) [名詞] 鳥
		StringBuffer questionBuffer = new StringBuffer(" (");
		String question_chi = question_voc.getChineseword();
		questionBuffer.append(abcd[randomIndex]+") ").append(question_chi);
		String question_eng = question_voc.getEnglishword();
		model.addAttribute("question_chi",questionBuffer.toString());
		model.addAttribute("question_eng",question_eng);
		
		
		// 判斷使用者回答是否正確 正確增加單字正確次數;反之增加單字錯誤次數
		if(userQuestion != null && userAnswer != null) {
			vocabularyService.checkUserAnswer(userQuestion,userAnswer);
		}
	
		return "vocabulary-choose-chi";
	}
	
}
	
		
	


