package com.nikhillab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/dashboard")
	public String dashbord(Model model) {
		model.addAttribute("title", "SCM - Dashboard");
		return "user/dashboard";
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("title", "SCM - Profile");
		return "user/profile";
	}
}
