package com.lee.vocabulary.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
}
