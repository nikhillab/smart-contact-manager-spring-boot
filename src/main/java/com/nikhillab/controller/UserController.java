package com.nikhillab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/dashboard")
	public String dashbord() {
		return "user/dashboard";
	}

	@GetMapping("/profile")
	public String profile() {
		
		return "user/profile";
	}
}
