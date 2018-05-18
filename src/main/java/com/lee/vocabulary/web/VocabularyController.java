package com.lee.vocabulary.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
			HttpSession httpSession,Model model) {
		
		// 判斷使用者第一次登入加入 session 作用域，否則直接取用session值
		if(username != null) {
			model.addAttribute("username", username);
			httpSession.setAttribute("username", username);
		} else {
			username = (String) httpSession.getAttribute("username");
		}
		
		return "vocabulary-write-eng";
	}
	
	// 判斷使用者的回答 增加資料庫中的單字對錯次數
	@RequestMapping(name="/vocabulary/api/addVocabularyRightOrWrongNum",method=RequestMethod.POST)
	@ResponseBody
	public boolean addVocabularyRightOrWrongNum(
			@RequestBody Map<String, String> jsonStr) {
		
		String question = jsonStr.get("question");
		String answer = jsonStr.get("answer");
		System.out.println(answer);
		System.out.println(question);

		if(question != null && answer != null) {
			vocabularyService.checkUserAnswer(question,answer);
		}
		
		return true;
	}
	
	// 產生亂數單字 api
	@ResponseBody
	@RequestMapping(value="/vocabulary/api/getRandomWord", method = RequestMethod.GET, produces = { "application/json" })
	public Vocabulary getRandomWord() {
		return vocabularyService.getRandomVocabulary();
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
	
	
	
	@GetMapping("/vocabulary/errorAnswer")
	public String vocabulary_error_anser(Model model) {
		List<Vocabulary> vocabularys = vocabularyService.getTopTenErrorAnswerVocabulary();
		model.addAttribute("vocabularys",vocabularys);
		return "vocabulary_error_answer";
	}
	
}
	
		
	


