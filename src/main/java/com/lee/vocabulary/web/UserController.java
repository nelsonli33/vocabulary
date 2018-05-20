package com.lee.vocabulary.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.vocabulary.entity.User;
import com.lee.vocabulary.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * 註冊/登入
	 */
	@GetMapping("/auth/signup")
	public String register() {
		return "register";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "login";
	}
	
	// 新增會員
	@RequestMapping(value="/users/api/newUser", method=RequestMethod.POST)
	@ResponseBody
	public boolean newUser(@RequestBody User user) {
		System.out.println(user.getEmail());
		User alreadyExistUser = userService.queryUserByAccount(user.getAccount());
		
		if(alreadyExistUser == null) {
			userService.insertNewUser(user);
		} else {
			return false;
		}
		return true;
	}
	
	// 會員登入
	@RequestMapping(value="/users/api/userLogin", method=RequestMethod.POST)
	@ResponseBody
	public boolean userLogin(@RequestBody Map<String,String> userJson, HttpSession session) {
		String account = userJson.get("account");
		String password = userJson.get("password");
		
		User user = userService.queryUserByAccountAndPassword(account,password);

		if(user != null) {
			session.setAttribute("currentUser", user);
			// session login
			return true;
		}
		return false;
	}
	
	@GetMapping("/auth/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
}
