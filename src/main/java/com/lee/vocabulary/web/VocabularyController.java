package com.lee.vocabulary.web;

import java.util.List;
import java.util.Map;

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

import com.lee.vocabulary.entity.User;
import com.lee.vocabulary.entity.Vocabulary;
import com.lee.vocabulary.service.VocabularyService;;

@Controller
public class VocabularyController {

	@Autowired
	private VocabularyService vocabularyService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@PostMapping("/vocabulary")
	public String handleURL(@RequestParam(required = false, name = "options") String testType) {

		if (testType.equals("write-eng")) {
			return "forward:/vocabulary/write-eng";
		} else if (testType.equals("choose-chi")) {
			return "forward:/vocabulary/choose-chi";
		}
		return "index";
	}

	@PostMapping("/vocabulary/write-eng")
	public String vocabulary_write_english(@RequestParam(required = false, name = "username") String username,
			HttpSession session, Model model) {

		if (username != null) {
			model.addAttribute("username", username);
		} else {
			User user = (User) session.getAttribute("currentUser");
			System.out.println(user.getAccount());
			model.addAttribute("username", user.getUsername());
		}
		return "vocabulary-write-eng";
	}

	// 判斷使用者的回答 增加資料庫中的單字對錯次數
	@RequestMapping(value = "/vocabulary/api/addVocabularyRightOrWrongNum", method = RequestMethod.POST)
	@ResponseBody
	public boolean addVocabularyRightOrWrongNum(@RequestBody Map<String, String> jsonStr) {

		String question = jsonStr.get("question");
		String answer = jsonStr.get("answer");
		System.out.println(answer);
		System.out.println(question);

		if (question != null && answer != null) {
			vocabularyService.checkUserAnswer(question, answer);
		}

		return true;
	}

	// 產生亂數單字 api
	@RequestMapping(value = "/vocabulary/api/getRandomVocabulary", method = RequestMethod.GET, produces = {
			"application/json" })
	@ResponseBody
	public Vocabulary getRandomVocabulary() {
		return vocabularyService.getRandomVocabulary();
	}

	// 產生四個亂數單字
	@RequestMapping(value = "/vocabulary/api/getRandomFourVocabulary", method = RequestMethod.GET, produces = {
			"application/json" })
	@ResponseBody
	public List<Vocabulary> getRandomFourVocabulary() {
		return vocabularyService.getRandomFourVocabulary();
	}

	@PostMapping("/vocabulary/choose-chi")
	public String vocabulary_choose_chinese(@RequestParam(required = false, name = "username") String username,
			HttpSession session, Model model) {

		// 判斷使用者第一次登入加入 cookie，否則直接取用session值
		if (username != null) {
			model.addAttribute("username", username);
		} else {
			User user = (User) session.getAttribute("currentUser");
			System.out.println(user.getAccount());
			model.addAttribute("username", user.getUsername());
		}

		return "vocabulary-choose-chi";
	}

	@GetMapping("/vocabulary/errorAnswer")
	public String vocabulary_error_anser(Model model) {
		List<Vocabulary> vocabularys = vocabularyService.getTopTenErrorAnswerVocabulary();
		model.addAttribute("vocabularys", vocabularys);
		return "vocabulary_error_answer";
	}

}
