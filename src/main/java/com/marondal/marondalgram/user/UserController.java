package com.marondal.marondalgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/login-view")
	public String inputLogin() {
		return "user/login";
	}
	
	@GetMapping("/join-view")
	public String inputJoin() {
		return "user/join";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		
		return "redirect:/user/login-view";
		
	}

}
